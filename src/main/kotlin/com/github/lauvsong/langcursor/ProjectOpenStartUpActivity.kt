package com.github.lauvsong.langcursor

import com.github.lauvsong.langcursor.services.LanguageCheckService
import com.github.lauvsong.langcursor.services.NotifyService
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import org.apache.commons.lang3.SystemUtils

class ProjectOpenStartUpActivity : StartupActivity.DumbAware {

    override fun runActivity(project: Project) {
        if (isNotSupportedOs()) {
            NotifyService.notifyNotSupportedOs(project)
        }

       initLanguageCheckService()
    }

    private fun isNotSupportedOs(): Boolean {
        return !(SystemUtils.IS_OS_WINDOWS || SystemUtils.IS_OS_MAC)
    }

    private fun initLanguageCheckService() {
        LanguageCheckService
    }
}
