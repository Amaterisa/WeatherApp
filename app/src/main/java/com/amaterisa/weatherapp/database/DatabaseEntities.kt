package com.amaterisa.weatherapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.amaterisa.weatherapp.network.model.WeatherForecastResponse

//@Entity
//data class DatabaseVideo constructor(
//    @PrimaryKey
//    val url: String,
//    val updated: String,
//    val title: String,
//    val description: String,
//    val thumbnail: String)
//
//fun List<DatabaseVideo>.asDomainModel(): List<WeatherForecastResponse> {
//    return map {
//        WeatherForecastResponse(
//            url = it.url,
//            title = it.title,
//            description = it.description,
//            updated = it.updated,
//            thumbnail = it.thumbnail)
//    }
//}