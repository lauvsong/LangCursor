package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.utils.NotifyUtil
import com.intellij.openapi.Disposable
import com.intellij.openapi.fileEditor.FileEditorManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.project.ProjectManager
import com.intellij.openapi.project.ProjectManagerListener
import com.intellij.openapi.startup.StartupActivity
import org.apache.commons.lang3.SystemUtils
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class ProjectOpenStartUpActivity : StartupActivity.DumbAware {

    private val scheduleExecutor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)

    override fun runActivity(project: Project) {
        if (isNotSupportedOs()) {
            notifyNotSupportedOs(project)
        }

        listenNotEnglishKeyLayout(project)
        disposeOnProjectClose(project)
    }

    private fun listenNotEnglishKeyLayout(project: Project) {
        // Reason for checking the key layout at a time interval, instead of just using 'KeyEvent':
        // In some scenarios, `KeyEvent` may not trigger for system keys.
        // Hence, there is a need to periodically check the key layout.
        // Based on testing results, a 200ms delay appears to be a sufficiently fast interval
        // to accommodate rapid language switching without noticeable lag to the user.
        val intervalMillis = 200L
        val task = Runnable {
            val editor = FileEditorManager.getInstance(project).selectedTextEditor
            if (editor != null) {
                CursorColorManager.updateCursorColor(editor)
            }
        }
        scheduleExecutor.scheduleAtFixedRate(task, 0, intervalMillis, TimeUnit.MILLISECONDS)
    }

    private fun disposeOnProjectClose(project: Project) {
        val disposable = createDisposableIfProjectClosed(project)
        project.messageBus.connect(disposable).subscribe(ProjectManager.TOPIC, object : ProjectManagerListener {
            override fun projectClosed(closedProject: Project) {
                if (project == closedProject) {
                    scheduleExecutor.shutdown()
                }
            }
        })
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
