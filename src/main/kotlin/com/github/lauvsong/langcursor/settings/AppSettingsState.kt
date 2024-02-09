package com.github.lauvsong.langcursor.settings

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.service
import com.intellij.ui.JBColor
import com.intellij.util.xmlb.XmlSerializerUtil
import java.awt.Color

@State(
    name = "com.github.lauvsong.langcursor.settings.AppSettingsState",
    storages = [Storage("AppSettingsState.xml")]
)
class AppSettingsState : PersistentStateComponent<AppSettingsState> {
    var nonEnglishCursorColorAsRgb: Int = JBColor.RED.rgb
    var capsLockCursorColorAsRgb: Int = JBColor.BLUE.rgb

    val nonEnglishCursorColor: Color
        get() = Color(nonEnglishCursorColorAsRgb)

    val capsLockCursorColor: Color
        get() = Color(capsLockCursorColorAsRgb)

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