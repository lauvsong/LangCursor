package com.github.lauvsong.langcursor

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.mockito.kotlin.times
import java.util.concurrent.TimeUnit
import org.mockito.Mockito // Required for timeout


// Using a mock InputChecker to verify interactions
@ExtendWith(org.mockito.junit.jupiter.MockitoExtension::class) // Optional: for cleaner mock initialization if needed
class InputCheckSchedulerTest {

    @Test
    fun `scheduler should call switchCursorColor periodically`() {
        val mockInputChecker = mock<InputChecker>()
        val scheduler = InputCheckScheduler(mockInputChecker)

        scheduler.start()

        // Verify that switchCursorColor is called at least N times within a certain period.
        // The interval is 200ms. Let's check for 3 calls, which should happen within ~600ms.
        // Mockito.timeout() will wait for the verification to pass up to a certain duration.
        verify(mockInputChecker, Mockito.timeout(800).atLeast(3)).switchCursorColor()

        scheduler.stop()

        // Wait a bit for the scheduler to fully stop and not make more calls
        Thread.sleep(300) // Sleep a bit longer than one interval

        // Verify that after stopping, switchCursorColor is not called anymore.
        // We capture the number of calls before stop and ensure no new calls are made.
        val interactionsBeforeStop = Mockito.mockingDetails(mockInputChecker).invocations.size
        Thread.sleep(300) // Wait again
        val interactionsAfterStopAttempt = Mockito.mockingDetails(mockInputChecker).invocations.size

        assert(interactionsBeforeStop == interactionsAfterStopAttempt) {
            "switchCursorColor should not be called after scheduler is stopped. " +
            "Before stop: $interactionsBeforeStop, After stop: $interactionsAfterStopAttempt"
        }
    }

    @Test
    fun `scheduler stop should prevent further calls`() {
        val mockInputChecker = mock<InputChecker>()
        val scheduler = InputCheckScheduler(mockInputChecker)

        scheduler.start()

        // Allow some calls to happen
        verify(mockInputChecker, Mockito.timeout(500).atLeastOnce()).switchCursorColor()

        scheduler.stop()
        
        // Capture the number of invocations after stop has been called
        val invocationsAfterStop = Mockito.mockingDetails(mockInputChecker).invocations.toList() // toList creates a copy

        // Wait for a duration longer than the scheduling interval
        Thread.sleep(300) // Interval is 200ms, wait for 300ms

        // Verify that no new invocations occurred after stopping
        val invocationsWellAfterStop = Mockito.mockingDetails(mockInputChecker).invocations
        
        assert(invocationsAfterStop.size == invocationsWellAfterStop.size) {
            "Expected no new calls to switchCursorColor after stop(). " +
            "Calls after stop: ${invocationsAfterStop.size}, " + 
            "Calls well after stop: ${invocationsWellAfterStop.size}"
        }
    }
}
