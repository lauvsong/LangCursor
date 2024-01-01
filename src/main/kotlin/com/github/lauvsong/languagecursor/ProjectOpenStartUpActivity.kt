package com.github.lauvsong.languagecursor

import com.github.lauvsong.languagecursor.listeners.NotEnglishKeyListener
import com.intellij.openapi.editor.EditorFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

class ProjectOpenStartUpActivity : StartupActivity.DumbAware {

    override fun runActivity(project: Project) {
        val editorFactory = EditorFactory.getInstance()
        val listener = NotEnglishKeyListener()
        editorFactory.eventMulticaster.addCaretListener(listener, project)
        if (isNotSupportedOs()) {
            notifyNotSupportedOs(project)
        }

    private fun isNotSupportedOs(): Boolean {
        return !(SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC)
    }

    private fun notifyNotSupportedOs(project: Project) {
        NotifyUtil.byBalloonWarning(project, """
            Language Cursor may not supported on your OS.
            Windows and macOS are supported.
        """.trimIndent())
    }

}
