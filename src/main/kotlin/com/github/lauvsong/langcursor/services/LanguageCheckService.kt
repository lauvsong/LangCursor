package com.github.lauvsong.langcursor.services

import com.github.lauvsong.langcursor.core.LanguageCheckStrategy
import com.github.lauvsong.langcursor.core.defaultLanguageCheckStrategy
import com.github.lauvsong.langcursor.core.windowsLanguageCheckStrategy
import org.apache.commons.lang3.SystemUtils
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