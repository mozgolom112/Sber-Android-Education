package com.mozgolom112.fundamentalsandroid.services

import android.app.Service
import android.content.Intent
import android.os.IBinder
import com.mozgolom112.fundamentalsandroid.dependency.Dependencies

const val SERVICE_INT_DATA = "SERVICE_INT_DATA"

class DownloadService : Service() {

    private val heavyWorkerManager by lazy { Dependencies.heavyWorkManager }

    override fun onBind(intent: Intent?): IBinder? {
        TODO("Not yet implemented")
    }

    override fun onStartCommand(intent: Intent, flags: Int, startId: Int): Int {
        val isEnable: Boolean? = intent.getBooleanExtra(SERVICE_INT_DATA, false)
        isEnable?.run {

            if (this && startId == 1) {
                //start work
                //startForeground(1, notificationsManager.notificationBuilder.build())
                heavyWorkerManager.startWork()
            }
        }
        return START_NOT_STICKY
    }

    override fun onDestroy() {
        super.onDestroy()
        heavyWorkerManager.run{
            resetProgress()
            onDestroy()
        }
    }

}

