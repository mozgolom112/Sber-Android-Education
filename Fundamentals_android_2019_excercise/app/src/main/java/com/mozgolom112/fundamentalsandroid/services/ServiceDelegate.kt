package com.mozgolom112.fundamentalsandroid.services

import android.app.Activity
import android.content.Intent

class ServiceDelegate {
    fun startDownloadService(activity: Activity, isEnable: Boolean) {
        val service = Intent(activity, DownloadService::class.java)
        service.putExtra(SERVICE_INT_DATA, isEnable)
        activity.startService(service)

    }

    fun stopDownloadService(activity: Activity) {
        val service = Intent(activity, DownloadService::class.java)
        activity.stopService(service)
    }

    fun startDownloadIntentService(activity: Activity, isEnable: Boolean) {
        val service = Intent(activity, DownloadIntentService::class.java)
        service.putExtra(SERVICE_INT_DATA, isEnable)
        activity.startService(service)
    }

    fun stopDownloadIntentService(activity: Activity) {
        val service = Intent(activity, DownloadIntentService::class.java)
        activity.stopService(service)
    }
}