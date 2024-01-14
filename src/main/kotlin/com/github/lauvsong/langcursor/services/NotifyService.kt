package com.github.lauvsong.langcursor.services

import com.github.lauvsong.langcursor.utils.NotifyUtil
import com.intellij.openapi.project.Project

object NotifyService {

    fun notifyNotSupportedOs(project: Project) {
        NotifyUtil.byBalloonWarning(
            project, """
            LangCursor may not supported on your OS.
            Windows and macOS are supported.
        """.trimIndent()
        )
    }
}