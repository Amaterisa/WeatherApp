package com.amaterisa.weatherapp.repository

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.amaterisa.weatherapp.database.WeatherForecastResponsesDatabase
import com.amaterisa.weatherapp.database.asDomainModel
import com.amaterisa.weatherapp.model.WeatherForecastResponse
import com.amaterisa.weatherapp.network.*
import com.amaterisa.weatherapp.weather.WeatherApiStatus
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherForecastResponseRepository(private val database: WeatherForecastResponsesDatabase) {

    val weathers: LiveData<List<WeatherForecastResponse>> =
        Transformations.map(database.weatherForecastResponseDao.getWeathers()) {
            it.asDomainModel()
        }

//    val currentWeather: WeatherForecastResponse =
//        database.weatherForecastResponseDao.getCurrentWeather().asDomainModel()
//        }

    suspend fun getCurrentWeather(): WeatherForecastResponse?{
        return database.weatherForecastResponseDao.getCurrentWeather()?.asDomainModel()
    }

    suspend fun refreshWeather() {
        try {
            withContext(Dispatchers.IO) {
                val weather =
                    WeatherApi.retrofitService.getWeather(MANAUS_LAT, MANAUS_LON, UNITS, API_KEY)
                        .await()
                Log.i("VIEWMODEl", weather.city.name)
                database.weatherForecastResponseDao.insert(weather.asDatabaseModel())
            }
            } catch (e: Exception) {
        }
    }
}
