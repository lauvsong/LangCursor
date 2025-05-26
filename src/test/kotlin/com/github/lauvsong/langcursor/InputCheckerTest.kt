package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.core.CapsLockCheckStrategy
import com.github.lauvsong.langcursor.core.LanguageCheckStrategy
import com.github.lauvsong.langcursor.services.CursorColorService
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.mockito.Mockito.*
import org.mockito.kotlin.mock
import org.mockito.kotlin.verify
import org.apache.commons.lang3.SystemUtils

// Mock SystemUtils.IS_OS_WINDOWS for consistent testing environment
// This might require PowerMockito or a similar library if SystemUtils is final,
// or refactoring InputChecker to make OS checking injectable.
// For now, we assume we can influence this, or test default paths.

class InputCheckerTest {

    private lateinit var mockLanguageCheckStrategy: LanguageCheckStrategy
    private lateinit var mockCapsLockCheckStrategy: CapsLockCheckStrategy
    private lateinit var mockCursorColorService: Unit // CursorColorService methods are static, so we'll use Mockito.mockStatic

    // Store the original strategies to restore them after tests
    private lateinit var originalLanguageCheckStrategy: LanguageCheckStrategy
    private lateinit var originalCapsLockCheckStrategy: CapsLockCheckStrategy
    
    // Hold the static mock for CursorColorService
    private lateinit var cursorColorServiceMockedStatic: org.mockito.MockedStatic<CursorColorService>


    @BeforeEach
    fun setUp() {
        // Mock dependencies
        mockLanguageCheckStrategy = mock<LanguageCheckStrategy>()
        mockCapsLockCheckStrategy = mock<CapsLockCheckStrategy>()

        // Mock static methods of CursorColorService
        // Note: mockStatic needs to be closed after tests.
        cursorColorServiceMockedStatic = mockStatic(CursorColorService::class.java)


        // Save original strategies from InputChecker (assuming they are accessible for testing)
        // This requires InputChecker's strategies to be accessible or injectable.
        // For this example, we'll assume we can temporarily replace them.
        // A better approach would be to make InputChecker's strategies injectable.

        // Hacky way to replace strategies in the object InputChecker for testing:
        // This uses reflection to modify private final fields. This is generally discouraged
        // and brittle. A refactor of InputChecker to allow strategy injection is better.
        val languageStrategyField = InputChecker::class.java.getDeclaredField("languageCheckStrategy")
        languageStrategyField.isAccessible = true
        originalLanguageCheckStrategy = languageStrategyField.get(InputChecker) as LanguageCheckStrategy
        languageStrategyField.set(InputChecker, mockLanguageCheckStrategy)

        val capsLockStrategyField = InputChecker::class.java.getDeclaredField("capsLockCheckStrategy")
        capsLockStrategyField.isAccessible = true
        originalCapsLockCheckStrategy = capsLockStrategyField.get(InputChecker) as CapsLockCheckStrategy
        capsLockStrategyField.set(InputChecker, mockCapsLockCheckStrategy)
    }

    @org.junit.jupiter.api.AfterEach
    fun tearDown() {
        // Restore original strategies in InputChecker
        val languageStrategyField = InputChecker::class.java.getDeclaredField("languageCheckStrategy")
        languageStrategyField.isAccessible = true
        languageStrategyField.set(InputChecker, originalLanguageCheckStrategy)

        val capsLockStrategyField = InputChecker::class.java.getDeclaredField("capsLockCheckStrategy")
        capsLockStrategyField.isAccessible = true
        capsLockStrategyField.set(InputChecker, originalCapsLockCheckStrategy)
        
        // Close the static mock
        cursorColorServiceMockedStatic.close()
    }

    @Test
    fun `switchCursorColor should set caps lock color when caps lock is on`() {
        `when`(mockCapsLockCheckStrategy.isCapsLockOn()).thenReturn(true)
        // We don't care about language check when caps lock is on for this test case
        // `when`(mockLanguageCheckStrategy.isEnglishInput()).thenReturn(true) // Or false, doesn't matter

        InputChecker.switchCursorColor()
        
        cursorColorServiceMockedStatic.verify { CursorColorService.toCapsLockCursorColor() }
        cursorColorServiceMockedStatic.verify(never()) { CursorColorService.toNonEnglishCursorColor() }
        cursorColorServiceMockedStatic.verify(never()) { CursorColorService.toEnglishCursorColor() }
    }

    @Test
    fun `switchCursorColor should set non-English color when caps lock is off and language is not English`() {
        `when`(mockCapsLockCheckStrategy.isCapsLockOn()).thenReturn(false)
        `when`(mockLanguageCheckStrategy.isEnglishInput()).thenReturn(false)

        InputChecker.switchCursorColor()

        cursorColorServiceMockedStatic.verify { CursorColorService.toNonEnglishCursorColor() }
        cursorColorServiceMockedStatic.verify(never()) { CursorColorService.toCapsLockCursorColor() }
        cursorColorServiceMockedStatic.verify(never()) { CursorColorService.toEnglishCursorColor() }
    }

    @Test
    fun `switchCursorColor should set English color when caps lock is off and language is English`() {
        `when`(mockCapsLockCheckStrategy.isCapsLockOn()).thenReturn(false)
        `when`(mockLanguageCheckStrategy.isEnglishInput()).thenReturn(true)

        InputChecker.switchCursorColor()

        cursorColorServiceMockedStatic.verify { CursorColorService.toEnglishCursorColor() }
        cursorColorServiceMockedStatic.verify(never()) { CursorColorService.toCapsLockCursorColor() }
        cursorColorServiceMockedStatic.verify(never()) { CursorColorService.toNonEnglishCursorColor() }
    }
}
