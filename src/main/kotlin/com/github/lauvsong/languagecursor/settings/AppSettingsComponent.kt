package com.github.lauvsong.languagecursor.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import java.awt.event.KeyAdapter
import java.awt.event.KeyEvent
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
    val keymap: JTextField = JTextField().apply {
        // Display the key in the text field every time the user enters a key
        addKeyListener(object : KeyAdapter() {
            override fun keyTyped(e: KeyEvent) {
                text = KeyEvent.getKeyText(e.keyCode)
            }
        })
    }
    val cursorColor: ColorPanel = ColorPanel()
}