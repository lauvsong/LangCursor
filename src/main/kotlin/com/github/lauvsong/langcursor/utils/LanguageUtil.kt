package com.github.lauvsong.langcursor.utils

import com.sun.jna.Library
import com.sun.jna.Native
import com.sun.jna.Pointer

interface LanguageUtil : Library {
    fun isEnglish(hWnd: Pointer?): Boolean

    companion object {
        val INSTANCE: LanguageUtil = Native.load("LanguageUtility", LanguageUtil::class.java)
    }
}