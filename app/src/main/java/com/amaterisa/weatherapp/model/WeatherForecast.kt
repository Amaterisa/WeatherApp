package com.amaterisa.weatherapp.model

import com.squareup.moshi.Json

data class WeatherForecast(
    @Json(name = "dt_txt")
    val date: String,
    val weather: List<Weather>,
    val main: Main
) {

    fun getDateUpdated(): String{
        return "Updated $date"
    }
}