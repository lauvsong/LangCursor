package com.github.lauvsong.languagecursor.settings

import com.intellij.openapi.options.Configurable
import javax.swing.JComponent

class AppSettingsConfigurable : Configurable {

    private lateinit var settingsComponent : AppSettingsComponent

    override fun getDisplayName(): String =
        "Language Cursor"

    override fun createComponent(): JComponent? {
        settingsComponent = AppSettingsComponent()
        return settingsComponent.mainPanel
    }

    override fun isModified(): Boolean {
        val settings = AppSettingsState.instance
        return settings.cursorColor != settingsComponent.cursorColor
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.cursorColorAsRgb = settingsComponent.cursorColor.selectedColor?.rgb ?: settings.cursorColorAsRgb
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        settingsComponent.cursorColor.selectedColor = settings.cursorColor
    }
}