package com.mozgolom112.fundamentalsandroid.services

import android.app.Activity
import android.content.Intent
import android.os.Build

class ServiceDelegate {
    fun startDownloadService(activity: Activity, isEnable: Boolean){
        val service = Intent(activity, DownloadService::class.java)
        service.putExtra(SERVICE_INT_DATA, isEnable)

        activity.startService(service)
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            activity.startForegroundService(service)
//        } else {
//            activity.startService(service)
//        }
    }
}