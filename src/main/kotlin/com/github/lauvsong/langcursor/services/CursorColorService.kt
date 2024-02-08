package com.github.lauvsong.langcursor.services

import com.github.lauvsong.langcursor.settings.AppSettingsState
import com.github.lauvsong.langcursor.utils.CursorColorUtil
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.ui.JBColor
import java.awt.Color

object CursorColorService {

    private val originalCursorColor: Color = EditorColorsManager.getInstance()
        .globalScheme
        .getColor(EditorColors.CARET_COLOR)
        ?: JBColor.BLACK

    fun toNonEnglishCursorColor() {
        val settings = AppSettingsState.instance
        CursorColorUtil.setGlobalCursorColor(settings.nonEnglishCursorColor)
    }

    fun toCapsLockCursorColor() {
        val settings = AppSettingsState.instance
        CursorColorUtil.setGlobalCursorColor(settings.capsLockCursorColor)
    }

    fun toOriginalCursorColor() {
        CursorColorUtil.setGlobalCursorColor(originalCursorColor)
    }
}
