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
    }

}
