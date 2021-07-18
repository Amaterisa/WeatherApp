package com.amaterisa.weatherapp.work

import android.content.Context
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.amaterisa.weatherapp.database.getDatabase
import com.amaterisa.weatherapp.network.MANAUS
import com.amaterisa.weatherapp.repository.WeatherForecastResponseRepository
import retrofit2.HttpException

class RequestDataWorker(appContext: Context, params: WorkerParameters):
    CoroutineWorker(appContext, params) {

    companion object {
        const val WORK_NAME = "RefreshDataWorker"
    }

    override suspend fun doWork(): Result {
        val database = getDatabase(applicationContext)
        val repository = WeatherForecastResponseRepository(database)
        return try {
            val weather = repository.getCurrentWeather()
            var city: String = MANAUS
            if (weather != null){
                city = weather.city?.name ?: city
            }
            repository.refreshWeather(city)
            Result.success()
        } catch (e: HttpException) {
            Result.retry()
        }
    }
}