package com.github.lauvsong.langcursor.utils

import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import java.awt.Color

object CursorColorUtil {

    fun setGlobalCursorColor(color: Color) {
        val globalScheme = EditorColorsManager.getInstance().globalScheme
        globalScheme.setColor(EditorColors.CARET_COLOR, color)
    }
}