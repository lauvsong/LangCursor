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
        return !((settings.keymap == settingsComponent.keymap.text)
                && (settings.cursorColor == settingsComponent.cursorColor.selectedColor))
    }

    override fun apply() {
        val settings = AppSettingsState.instance
        settings.keymap = settingsComponent.keymap.text
        settings.cursorColor = settingsComponent.cursorColor.selectedColor ?: settings.cursorColor
    }

    override fun reset() {
        val settings = AppSettingsState.instance
        settingsComponent.keymap.text = settings.keymap
        settingsComponent.cursorColor.selectedColor = settings.cursorColor
    }
}