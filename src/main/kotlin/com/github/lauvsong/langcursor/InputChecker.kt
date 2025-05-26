package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.core.CapsLockCheckStrategy
import com.github.lauvsong.langcursor.core.LanguageCheckStrategy
import com.github.lauvsong.langcursor.core.defaultCapsLockCheckStrategy
import com.github.lauvsong.langcursor.core.defaultLanguageCheckStrategy
import com.github.lauvsong.langcursor.core.windowsLanguageCheckStrategy
import com.github.lauvsong.langcursor.services.CursorColorService
import org.apache.commons.lang3.SystemUtils

object InputChecker {

    private val languageCheckStrategy: LanguageCheckStrategy = setLanguageCheckStrategy()
    private val capsLockCheckStrategy: CapsLockCheckStrategy = defaultCapsLockCheckStrategy

    // Make this method public
    fun switchCursorColor() {
        if (capsLockCheckStrategy.isCapsLockOn()) {
            CursorColorService.toCapsLockCursorColor()
        } else if (!languageCheckStrategy.isEnglishInput()) {
            CursorColorService.toNonEnglishCursorColor()
        } else {
            CursorColorService.toEnglishCursorColor()
        }
    }

    private fun setLanguageCheckStrategy(): LanguageCheckStrategy =
        if (SystemUtils.IS_OS_WINDOWS) {
            windowsLanguageCheckStrategy
        }  else {
            defaultLanguageCheckStrategy
        }
}