package com.amaterisa.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.amaterisa.weatherapp.database.WeatherForecastResponsesDatabase
import com.amaterisa.weatherapp.database.asDomainModel
import com.amaterisa.weatherapp.model.WeatherForecastResponse
import com.amaterisa.weatherapp.network.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherForecastResponseRepository(private val database: WeatherForecastResponsesDatabase) {

    val weathers: LiveData<List<WeatherForecastResponse>> =
        Transformations.map(database.weatherForecastResponseDao().getWeathers()) {
            it.asDomainModel()
        }

    val currentWeather: LiveData<WeatherForecastResponse> =
        Transformations.map(database.weatherForecastResponseDao().getCurrentWeather()) {
            it.asDomainModel()
        }

    suspend fun refreshWeather() {
        withContext(Dispatchers.IO) {
            val weather =
                WeatherApi.retrofitService.getWeather(MANAUS_LAT, MANAUS_LON, UNITS, API_KEY)
                    .await()
            database.weatherForecastResponseDao().insert(weather.asDatabaseModel())
        }
    }
}
