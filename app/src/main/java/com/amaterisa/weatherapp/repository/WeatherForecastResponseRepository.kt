package com.amaterisa.weatherapp.repository

import android.util.Log
import com.amaterisa.weatherapp.database.WeatherForecastResponsesDatabase
import com.amaterisa.weatherapp.database.asDomainModel
import com.amaterisa.weatherapp.model.WeatherForecastResponse
import com.amaterisa.weatherapp.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherForecastResponseRepository(private val database: WeatherForecastResponsesDatabase) {

    suspend fun getCurrentWeather(): WeatherForecastResponse?{
        return database.weatherForecastResponseDao.getCurrentWeather()?.asDomainModel()
    }

    suspend fun refreshWeather(city: String) {
        try {
            withContext(Dispatchers.IO) {
                val weather =
                    WeatherApi.retrofitService.getWeather(city, UNITS, API_KEY)
                        .await()
                Log.i("refreshWeather", weather.id.toString())
                database.weatherForecastResponseDao.insert(weather.asDatabaseModel())
            }
            } catch (e: Exception) {
        }
    }
}
