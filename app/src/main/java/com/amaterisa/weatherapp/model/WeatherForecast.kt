package com.amaterisa.weatherapp.model

import com.squareup.moshi.Json

data class WeatherForecast(
    @Json(name = "dt_txt")
    val date: String,
    val weather: List<Weather>,
    val main: Main
) {

    fun getDateUpdated(): String{
        return "Updated $date UTC"
    }

    fun getDay(): String{
        return "Day " + date.substring(8,10)
    }

    fun getHour(): String{
        return date.substring(11,16)
    }
}