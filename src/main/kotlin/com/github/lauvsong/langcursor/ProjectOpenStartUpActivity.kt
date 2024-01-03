package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.utils.NotifyUtil
import com.intellij.ide.IdeEventQueue
import com.intellij.openapi.Disposable
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.startup.StartupActivity
import org.apache.commons.lang3.SystemUtils

class ProjectOpenStartUpActivity : StartupActivity.DumbAware {

    override fun runActivity(project: Project) {
        if (isNotSupportedOs()) {
            notifyNotSupportedOs(project)
        }

        listenNotEnglishKeyLayout(project)
    }

    private fun listenNotEnglishKeyLayout(project: Project) {
        // Why trigger listener logic by all type of event:
        // In some cases, `KeyEvent` may not trigger for system keys.
        // Although monitoring all events may impact performance, this approach is the only way I found
        // to detect language changes when `KeyEvent` is not working,
        IdeEventQueue.getInstance().addDispatcher({ _ ->
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            if (editor != null) {
                CursorColorManager.updateCursorColor(editor)
            }
            false
        }, createDisposableIfProjectClosed(project))
    }

    private fun createDisposableIfProjectClosed(project: Project): Disposable {
        val connection = project.messageBus.connect()
        connection.subscribe(ProjectManager.TOPIC, object : ProjectManagerListener {
            override fun projectClosed(project: Project) {
                connection.dispose()
            }
        })
        return connection
    }

    private fun isNotSupportedOs(): Boolean {
        return !(SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC)
    }

    private fun notifyNotSupportedOs(project: Project) {
        NotifyUtil.byBalloonWarning(project, """
            LangCursor may not supported on your OS.
            Windows and macOS are supported.
        """.trimIndent())
    }

}
