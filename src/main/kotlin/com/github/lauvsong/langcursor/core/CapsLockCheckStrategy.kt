package com.github.lauvsong.langcursor.core

import java.awt.Toolkit
import java.awt.event.KeyEvent

fun interface CapsLockCheckStrategy {
    fun isCapsLockOn() : Boolean

}

val defaultCapsLockCheckStrategy = CapsLockCheckStrategy {
    val capsLock = Toolkit.getDefaultToolkit().getLockingKeyState(KeyEvent.VK_CAPS_LOCK)
    capsLock
}