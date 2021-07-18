package com.amaterisa.weatherapp

import android.app.Application
import androidx.work.*
import com.amaterisa.weatherapp.work.RequestDataWorker
import com.amaterisa.weatherapp.work.RequestDataWorker.Companion.WORK_NAME
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

class WeatherApplication : Application() {

    private val applicationScope = CoroutineScope(Dispatchers.Default)

    private val constraints = Constraints.Builder()
        .setRequiresBatteryNotLow(true)
        .build()

    private val repeatingRequest: PeriodicWorkRequest
            = PeriodicWorkRequestBuilder<RequestDataWorker>(3, TimeUnit.HOURS)
        .setConstraints(constraints)
        .build()

    private fun delayedInit() {
        applicationScope.launch {
            setupRecurringWork()
        }
    }

    private fun setupRecurringWork() {
        WorkManager.getInstance(this).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.REPLACE,
            repeatingRequest)

    }


    override fun onCreate() {
        super.onCreate()
        delayedInit()
    }
}