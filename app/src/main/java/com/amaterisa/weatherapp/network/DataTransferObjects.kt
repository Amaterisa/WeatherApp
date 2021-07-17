package com.amaterisa.weatherapp.network

import android.util.Log
import com.amaterisa.weatherapp.database.DatabaseWeatherForecastResponse
import com.amaterisa.weatherapp.model.City
import com.amaterisa.weatherapp.model.WeatherForecast
import com.amaterisa.weatherapp.model.WeatherForecastResponse
import com.squareup.moshi.JsonClass

data class NetworkWeatherForecastResponseContainer(val weathers: List<NetworkWeatherForecastResponse>)

data class NetworkWeatherForecastResponse(
    val list: List<WeatherForecast>,
    val city: City,
    val id: Long?,
)

fun NetworkWeatherForecastResponse.asDatabaseModel(): DatabaseWeatherForecastResponse {
    return DatabaseWeatherForecastResponse(
            list = this.list,
            city = this.city,
            id = this.id)
}

fun NetworkWeatherForecastResponseContainer.asDomainModel(): List<WeatherForecastResponse> {
    return weathers.map {
        WeatherForecastResponse(
            list = it.list,
            city = it.city,
            id = it.id)
    }
}