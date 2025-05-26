package com.github.lauvsong.langcursor

import java.util.concurrent.Executors
import java.util.concurrent.ScheduledExecutorService
import java.util.concurrent.TimeUnit

class InputCheckScheduler(private val inputChecker: InputChecker) {

    private var scheduledExecutor: ScheduledExecutorService? = null
    private val INTERVAL_MILLIS = 200L

    fun start() {
        if (scheduledExecutor == null || scheduledExecutor!!.isShutdown) {
            scheduledExecutor = Executors.newScheduledThreadPool(1)
        }
        scheduledExecutor?.scheduleAtFixedRate(
            { inputChecker.switchCursorColor() }, // This will be made public in InputChecker later
            0,
            INTERVAL_MILLIS,
            TimeUnit.MILLISECONDS
        )
    }

    fun stop() {
        scheduledExecutor?.shutdown()
        try {
            if (scheduledExecutor?.awaitTermination(INTERVAL_MILLIS, TimeUnit.MILLISECONDS) == false) {
                scheduledExecutor?.shutdownNow()
            }
        } catch (e: InterruptedException) {
            scheduledExecutor?.shutdownNow()
            Thread.currentThread().interrupt()
        }
    }
}
