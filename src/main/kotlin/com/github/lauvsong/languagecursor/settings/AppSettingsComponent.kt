package com.github.lauvsong.languagecursor.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel
import javax.swing.JTextField

class AppSettingsComponent {
    private val mainPanel: JPanel by lazy {
        FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Language transform keymap: "), keymapField, 1, false)
            .addLabeledComponent(JBLabel("Cursor color: "), cursorColorField, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }
    private val keymapField: JTextField = JTextField()
    private val cursorColorField: ColorPanel = ColorPanel()
}