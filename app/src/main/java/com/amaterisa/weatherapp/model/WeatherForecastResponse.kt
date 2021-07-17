package com.amaterisa.weatherapp.network.model

data class WeatherForecastResponse(
    val list: List<WeatherForecast>,
    val city: City?,
    val id: Long?,
    ) {
}