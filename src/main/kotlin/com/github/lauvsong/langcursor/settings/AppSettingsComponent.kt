package com.github.lauvsong.langcursor.settings

import com.intellij.ui.ColorPanel
import com.intellij.ui.components.JBLabel
import com.intellij.util.ui.FormBuilder
import javax.swing.JPanel

class AppSettingsComponent {
    val mainPanel: JPanel by lazy {
        FormBuilder.createFormBuilder()
            .addLabeledComponent(JBLabel("English Cursor color: "), englishCursorColor, 1, false)
            .addLabeledComponent(JBLabel("Non-English Cursor color: "), nonEnglishCursorColor, 2, false)
            .addLabeledComponent(JBLabel("CapsLock Cursor color: "), capsLockCursorColor, 3, false)
            .addComponentFillVertically(JPanel(), 0)
            .panel
    }

    val englishCursorColor: ColorPanel = ColorPanel()

    val nonEnglishCursorColor: ColorPanel = ColorPanel()

    val capsLockCursorColor: ColorPanel = ColorPanel()

}