package com.github.lauvsong.langcursor.core

import com.github.lauvsong.langcursor.utils.WindowsLanguageUtil
import com.jcraft.jsch.agentproxy.connector.PageantConnector
import java.awt.im.InputContext
import java.util.*

fun interface LanguageCheckStrategy {
    fun isEnglishInput(): Boolean
}

val defaultLanguageCheckStrategy = LanguageCheckStrategy {
    val locale = InputContext.getInstance().locale
    val language = locale.language
    val country = locale.country

    language == Locale.ENGLISH.language
            || country == Locale.US.country
            || country == Locale.UK.country
}

val windowsLanguageCheckStrategy = LanguageCheckStrategy {
    PageantConnector.User32.INSTANCE.GetForegroundWindow()
        ?.let { hwnd ->
            WindowsLanguageUtil.INSTANCE.isEnglish(hwnd.pointer)
        } ?: false
}