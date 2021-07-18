package com.amaterisa.weatherapp.network

import com.amaterisa.weatherapp.database.DatabaseWeatherForecastResponse
import com.amaterisa.weatherapp.model.City
import com.amaterisa.weatherapp.model.WeatherForecast

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