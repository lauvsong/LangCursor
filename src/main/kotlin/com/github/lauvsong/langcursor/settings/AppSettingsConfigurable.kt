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
        return settings.nonEnglishCursorColor != settingsComponent.nonEnglishCursorColor
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.nonEnglishCursorColorAsRgb = settingsComponent.nonEnglishCursorColor.selectedColor?.rgb ?: settings.nonEnglishCursorColorAsRgb
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        settingsComponent.nonEnglishCursorColor.selectedColor = settings.nonEnglishCursorColor
        settingsComponent.capsLockCursorColor.selectedColor = settings.capsLockCursorColor
        settingsComponent.notEnglishCapsLockCursorColor.selectedColor = settings.nonEnglishCapsLockCursorColor
    }
}