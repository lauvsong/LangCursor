package com.github.lauvsong.langcursor.services

import java.awt.im.InputContext
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object LanguageCheckService {

    private val scheduledExecutor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private const val INTERVAL_MILLIS = 200L

    fun start() {
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
        if (isEnglishInput()) {
            CursorColorService.toOriginalCursorColor()
        } else {
            CursorColorService.toNotEnglishCursorColor()
        }
    }

    private fun isEnglishInput(): Boolean {
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
            || country == Locale.UK.country
        ) {
            return true
        }

        return false
    }
}