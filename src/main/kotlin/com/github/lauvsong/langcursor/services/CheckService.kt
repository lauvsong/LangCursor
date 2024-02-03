package com.github.lauvsong.langcursor.services

import com.github.lauvsong.langcursor.core.*
import org.apache.commons.lang3.SystemUtils
import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

object CheckService {

    private val languageCheckStrategy: LanguageCheckStrategy = setLanguageCheckStrategy()
    private val capsLockCheckStrategy: CapsLockCheckStrategy = defaultCapsLockCheckStrategy
    private val scheduledExecutor: ScheduledExecutorService = Executors.newScheduledThreadPool(1)
    private const val INTERVAL_MILLIS = 200L

    init {
        start()
    }

    private fun start() {
        scheduledExecutor.scheduleAtFixedRate(
            { switchCursorColor() },
            0,
            INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
    }

    fun shutdown() {
        scheduledExecutor.shutdown()
    }

    private fun switchCursorColor() {
        if (languageCheckStrategy.isEnglishInput()) {
            if (capsLockCheckStrategy.isCapsLockOn()) {
                CursorColorService.toCapsLockCursorColor()
            } else {
                CursorColorService.toOriginalCursorColor()
            }
        } else {
            if (capsLockCheckStrategy.isCapsLockOn()) {
                CursorColorService.toNotEnglishCapsLockCursorColor()
            } else {
                CursorColorService.toNotEnglishCursorColor()
            }
        }
    }

    private fun setLanguageCheckStrategy(): LanguageCheckStrategy =
        if (SystemUtils.IS_OS_WINDOWS) {
            windowsLanguageCheckStrategy
        }  else {
            defaultLanguageCheckStrategy
        }
}