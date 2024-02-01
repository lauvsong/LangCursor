package com.github.lauvsong.langcursor.services

import java.awt.im.InputContext
import com.github.lauvsong.langcursor.utils.LanguageUtil
import com.jcraft.jsch.agentproxy.connector.PageantConnector.User32
import org.apache.commons.lang3.SystemUtils
import java.util.*
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object LanguageCheckService {

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
        if (isEnglishInput()) {
            CursorColorService.toOriginalCursorColor()
        } else {
            CursorColorService.toNotEnglishCursorColor()
        }
    }

    private fun isEnglishInput(): Boolean {

        if (SystemUtils.IS_OS_WINDOWS){
            var hwnd = User32.INSTANCE.GetForegroundWindow()

            if (hwnd == null)
                return false

            val isEnglish: Boolean = LanguageUtil.INSTANCE.isEnglish(hwnd.pointer)

            if (isEnglish)
                return true

            return false
        }
        else
        {
            val locale = InputContext.getInstance().locale
            val language = locale.language
            val country = locale.country

            if (language == Locale.ENGLISH.language)
                return true

            if (country == Locale.US.country || country == Locale.UK.country)
                return true
        }

        return false
    }
}