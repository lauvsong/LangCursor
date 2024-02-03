package com.github.lauvsong.langcursor.services

import com.github.lauvsong.langcursor.utils.LanguageUtil
import com.jcraft.jsch.agentproxy.connector.PageantConnector.User32
import org.apache.commons.lang3.SystemUtils
import java.awt.im.InputContext
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object LanguageCheckService {

    private val languageCheckStrategy: LanguageCheckStrategy = setLanguageCheckStrategy()
    private val scheduledExecutor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private const val INTERVAL_MILLIS = 200L

    init {
        start()
    }

    private fun start() {
        scheduledExecutor.scheduleAtFixedRate(
            { switchCursorColorByLanguage() },
            0,
            INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
    }

    fun shutdown() {
        scheduledExecutor.shutdown()
    }

    private fun switchCursorColorByLanguage() {
        if (languageCheckStrategy.isEnglishInput()) {
            CursorColorService.toOriginalCursorColor()
        } else {
            CursorColorService.toNotEnglishCursorColor()
        }
    }

    private fun setLanguageCheckStrategy(): LanguageCheckStrategy =
        if (SystemUtils.IS_OS_WINDOWS) {
            windowsLanguageCheckStrategy
        }  else {
            defaultLanguageCheckStrategy
        }
}

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
    User32.INSTANCE.GetForegroundWindow()
        ?.let { hwnd ->
            LanguageUtil.INSTANCE.isEnglish(hwnd.pointer)
        } ?: false
}