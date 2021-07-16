package com.amaterisa.weatherapp.network.model

data class WeatherProperty(
    val weather: List<Weather>,
    val main: Main,
    val name: String
) {
}