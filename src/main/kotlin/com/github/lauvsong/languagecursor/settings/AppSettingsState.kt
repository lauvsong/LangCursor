package com.github.lauvsong.languagecursor.settings

import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.ui.JBColor
import com.intellij.util.xmlb.XmlSerializerUtil
import org.jetbrains.annotations.NotNull
import java.awt.Color

@State(
    name = "com.github.lauvsong.languagecursor.settings.AppSettingsState",
    storages = [Storage("AppSettingsState.xml")]
)
internal class AppSettingsState : PersistentStateComponent<AppSettingsState> {
    var keymap: String = ""
    var cursorColor: Color = JBColor.BLUE

    override fun getState(): AppSettingsState = this

    override fun loadState(@NotNull state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: AppSettingsState
            get() = ApplicationManager.getApplication().service()
    }
}