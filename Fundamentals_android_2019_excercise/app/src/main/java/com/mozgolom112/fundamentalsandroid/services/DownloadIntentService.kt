package com.mozgolom112.fundamentalsandroid.services

import android.app.IntentService
import android.content.Intent
import android.os.Handler
import android.os.Looper
import com.mozgolom112.fundamentalsandroid.dependency.Dependencies

private const val SERVICE_INTENT_PROGRESS = "IntentServiceProgress"

class DownloadIntentService : IntentService(SERVICE_INTENT_PROGRESS) {

    override fun onHandleIntent(intent: Intent?) {
        val params: Boolean? = intent?.getBooleanExtra(SERVICE_INT_DATA, false)

        Handler(Looper.getMainLooper()).post {
            params?.run { startWork(this) }
        }
        Thread.sleep(DELAY_VALUE)//note, this is a random number, for an example hard work
    }

    private fun startWork(isEnable: Boolean) {
        isEnable.run {
            when {
                this -> {
                    Dependencies.run {
                        notificationsManager.showNotification()
                        heavyWorkManager.startWork()
                    }
                }
                else -> {
                    Dependencies.run {
                        notificationsManager.hideNotification()
                        heavyWorkManager.resetProgress()
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        Handler(Looper.getMainLooper()).post {
            Dependencies.run {
                heavyWorkManager.run {
                    finishedWork()
                    onDestroy()
                }
                notificationsManager.hideNotification()
            }
        }

    }
}