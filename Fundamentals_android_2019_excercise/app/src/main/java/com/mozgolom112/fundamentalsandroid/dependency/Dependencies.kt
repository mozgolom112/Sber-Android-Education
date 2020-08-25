package com.mozgolom112.fundamentalsandroid.dependency

import com.mozgolom112.fundamentalsandroid.services.HeavyWorkerManager
import com.mozgolom112.fundamentalsandroid.services.ServiceDelegate

object Dependencies {

    val heavyWorkManager by lazy {
        createHeavyWorkManager()
    }

    val serviceDelegate by lazy {
        createServiceDelegate()
    }

    private fun createServiceDelegate(): ServiceDelegate = ServiceDelegate()

    private fun createHeavyWorkManager(): HeavyWorkerManager = HeavyWorkerManager()
}