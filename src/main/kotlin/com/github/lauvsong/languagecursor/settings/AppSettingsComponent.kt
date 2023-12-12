package com.github.lauvsong.languagecursor.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel
import javax.swing.JTextField

class AppSettingsComponent {
    val mainPanel: JPanel by lazy {
        FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Language transform keymap: "), keymap, 1, false)
            .addLabeledComponent(JBLabel("Cursor color: "), cursorColor, 1, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }
    val keymap: JTextField = JTextField()
    val cursorColor: ColorPanel = ColorPanel()
}