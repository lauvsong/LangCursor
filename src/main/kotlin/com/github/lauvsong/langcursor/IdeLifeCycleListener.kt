package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.services.CheckService
import com.intellij.ide.AppLifecycleListener

class IdeLifeCycleListener : AppLifecycleListener {

    override fun appClosing() {
        super.appClosing()
        CheckService.shutdown()
    }
}