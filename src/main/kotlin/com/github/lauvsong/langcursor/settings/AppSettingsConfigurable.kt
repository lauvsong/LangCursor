package com.github.lauvsong.langcursor.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class AppSettingsConfigurable : Configurable {

    private lateinit var settingsComponent : AppSettingsComponent

    override fun getDisplayName(): String =
        "LangCursor"

    override fun createComponent(): JComponent {
        settingsComponent = AppSettingsComponent()
        return settingsComponent.mainPanel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        return ((settings.englishCursorColor != settingsComponent.englishCursorColor)
                || (settings.nonEnglishCursorColor != settingsComponent.nonEnglishCursorColor)
                || (settings.capsLockCursorColor != settingsComponent.capsLockCursorColor))
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.englishCursorColorAsRgb = settingsComponent.englishCursorColor.selectedColor?.rgb ?: settings.englishCursorColorAsRgb
        settings.nonEnglishCursorColorAsRgb = settingsComponent.nonEnglishCursorColor.selectedColor?.rgb ?: settings.nonEnglishCursorColorAsRgb
        settings.capsLockCursorColorAsRgb = settingsComponent.capsLockCursorColor.selectedColor?.rgb ?: settings.capsLockCursorColorAsRgb
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        settingsComponent.englishCursorColor.selectedColor = settings.englishCursorColor
        settingsComponent.nonEnglishCursorColor.selectedColor = settings.nonEnglishCursorColor
        settingsComponent.capsLockCursorColor.selectedColor = settings.capsLockCursorColor
    }
}