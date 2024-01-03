package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.settings.AppSettingsState
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.colors.EditorColors
import com.intellij.openapi.editor.colors.EditorColorsManager
import com.intellij.ui.JBColor
import java.awt.Color
import java.awt.im.InputContext
import java.util.Locale

object CursorColorManager {

    private val originalCursorColor: Color = EditorColorsManager.getInstance()
        .globalScheme
        .getColor(EditorColors.CARET_COLOR)
        ?: JBColor.BLACK

    fun updateCursorColor(editor: Editor) {
        val settings = AppSettingsState.instance
        val isEnglishInput = isEnglishInput()
        val cursorColor = if (!isEnglishInput) settings.cursorColor else editor.colorsScheme.defaultForeground

        if (isEnglishInput) {
            restoreOriginalCursorColor(editor)
        } else {
            noEnglishCursorColor(editor, cursorColor)
        }
    }

    private fun isEnglishInput() : Boolean {
        val locale = InputContext.getInstance().locale
        val language = locale.language
        val country = locale.country

        // Explanation for not solely relying on constant `Locale.ENGLISH`:
        // In some Locale configurations, the language code might not be explicitly provided.
        // e.g., "_US_UserDefined_252"
        // Therefore, Locales with country codes are also considered as English input.
        // Canada (CA) is not included here because both English and French are used in Canada.
        // ** If you encounter any edge cases, please feel free to open an issue on GitHub. **
        if (language == Locale.ENGLISH.language) {
            return true
        }

        if (country == Locale.US.country
            || country == Locale.UK.country) {
            return true
        }

        return false
    }

    private fun noEnglishCursorColor(editor: Editor, newColor: Color) {
        val scheme = editor.colorsScheme
        scheme.setColor(EditorColors.CARET_COLOR, newColor)
    }

    private fun restoreOriginalCursorColor(editor: Editor) {
        val scheme = editor.colorsScheme
        scheme.setColor(EditorColors.CARET_COLOR, originalCursorColor)
    }

}