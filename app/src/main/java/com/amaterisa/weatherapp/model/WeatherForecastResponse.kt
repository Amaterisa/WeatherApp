package com.amaterisa.weatherapp.model

data class WeatherForecastResponse(
    val list: List<WeatherForecast>,
    val city: City?,
    val id: Long?,
    ) {
}