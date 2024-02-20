package com.github.lauvsong.langcursor.services

import com.github.lauvsong.langcursor.settings.AppSettingsState
import com.github.lauvsong.langcursor.utils.CursorColorUtil

object CursorColorService {

    fun toNonEnglishCursorColor() {
        val settings = AppSettingsState.instance
        CursorColorUtil.setGlobalCursorColor(settings.nonEnglishCursorColor)
    }

    fun toCapsLockCursorColor() {
        val settings = AppSettingsState.instance
        CursorColorUtil.setGlobalCursorColor(settings.capsLockCursorColor)
    }

    fun toEnglishCursorColor() {
        val settings = AppSettingsState.instance
        CursorColorUtil.setGlobalCursorColor(settings.englishCursorColor)
    }
}
