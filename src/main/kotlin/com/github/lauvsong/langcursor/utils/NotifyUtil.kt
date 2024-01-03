package com.github.lauvsong.langcursor.utils

import com.intellij.notification.NotificationGroupManager
import com.intellij.notification.NotificationType
import com.intellij.openapi.project.Project

object NotifyUtil {

    fun byBalloonWarning(project: Project, content: String) {
        NotificationGroupManager.getInstance()
            .getNotificationGroup("balloon")
            .createNotification(content, NotificationType.WARNING)
            .notify(project)
    }
}