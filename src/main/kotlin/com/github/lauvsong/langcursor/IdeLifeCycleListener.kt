package com.github.lauvsong.langcursor

import com.intellij.ide.AppLifecycleListener

class IdeLifeCycleListener : AppLifecycleListener {

    override fun appClosing() {
        super.appClosing()
        InputChecker.shutdown()
    }
}