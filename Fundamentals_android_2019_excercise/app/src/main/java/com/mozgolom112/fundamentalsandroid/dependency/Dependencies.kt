package com.mozgolom112.fundamentalsandroid.dependency

import android.app.Application
import android.app.NotificationManager
import android.content.Context.NOTIFICATION_SERVICE
import com.mozgolom112.fundamentalsandroid.App
import com.mozgolom112.fundamentalsandroid.services.HeavyWorkerManager
import com.mozgolom112.fundamentalsandroid.services.ServiceDelegate
import com.mozgolom112.fundamentalsandroid.support.utils.NotificationsManager
import com.mozgolom112.fundamentalsandroid.support.utils.ResourceManager

object Dependencies {

    val heavyWorkManager by lazy {
        createHeavyWorkManager()
    }

    val serviceDelegate by lazy {
        createServiceDelegate()
    }

    val notificationsManager by lazy {
        createNotificationsManager()
    }

    private fun createNotificationsManager(): NotificationsManager {
        return NotificationsManager(
            App.instance,
            ResourceManager(App.instance),
            App.instance.getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        )
    }

    private fun createServiceDelegate(): ServiceDelegate = ServiceDelegate()

    private fun createHeavyWorkManager(): HeavyWorkerManager = HeavyWorkerManager()

}