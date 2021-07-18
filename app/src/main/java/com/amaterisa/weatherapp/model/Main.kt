package com.amaterisa.weatherapp.model

import com.squareup.moshi.Json

data class Main(
    val temp: Double,
    @Json(name = "feels_like") val feelsLike: Double,
    @Json(name = "temp_min") val minTemp: Double,
    @Json(name = "temp_max") val maxTemp: Double,
    val pressure: Double,
    val humidity: Double
) {

    fun getTempString(): String {
        return temp.toString().substringBefore(".") + "°C"
    }

    fun getMinTempString(): String {
        return "Min Temp " + minTemp.toString().substringBefore(".") + "°C"
    }

    fun getMaxTempString(): String {
        return "Max Temp " + maxTemp.toString().substringBefore(".") + "°C"
    }

    fun getReducedMinTempString(): String {
        return minTemp.toString().substringBefore(".") + "°C"
    }

    fun getReducedMaxTempString(): String {
        return maxTemp.toString().substringBefore(".") + "°C"
    }
}