package com.github.lauvsong.langcursor.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

class AppSettingsComponent {
    val mainPanel: JPanel by lazy {
        FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("Cursor color: "), nonEnglishCursorColor, 1, false)
            .addLabeledComponent(JBLabel("CapsLock cursor color: "), capsLockCursorColor, 2, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val nonEnglishCursorColor: ColorPanel = ColorPanel()

    val capsLockCursorColor: ColorPanel = ColorPanel()

}