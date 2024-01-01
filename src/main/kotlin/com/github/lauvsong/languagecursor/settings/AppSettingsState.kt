package com.github.lauvsong.languagecursor.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.ui.JBColor
import com.intellij.util.xmlb.XmlSerializerUtil
import java.awt.Color

@State(
    name = "com.github.lauvsong.languagecursor.settings.AppSettingsState",
    storages = [Storage("AppSettingsState.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState> {
    var cursorColorAsRgb: Int = JBColor.RED.rgb

    val cursorColor: Color
        get() = Color(cursorColorAsRgb)

    override fun getState(): AppSettingsState {
        return this
    }

    override fun loadState(state: AppSettingsState) {
        XmlSerializerUtil.copyBean(state, this)
    }

    companion object {
        val instance: AppSettingsState
            get() = service()
    }
}