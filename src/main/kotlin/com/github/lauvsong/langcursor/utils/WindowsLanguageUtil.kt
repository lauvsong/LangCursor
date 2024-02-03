package com.github.lauvsong.langcursor.utils

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer

interface WindowsLanguageUtil : Library {
    fun isEnglish(hWnd: Pointer?): Boolean

    companion object {
        val INSTANCE: WindowsLanguageUtil = Native.load("LanguageUtility", WindowsLanguageUtil::class.java)
    }
}