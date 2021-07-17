package com.amaterisa.weatherapp.network.model

import com.squareup.moshi.Json

data class WeatherForecast(
    @Json(name = "dt_txt")
    val date: String,

    val weather: List<Weather>,

    val main: Main
) {
}