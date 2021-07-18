package com.amaterisa.weatherapp.database

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.amaterisa.weatherapp.model.City
import com.amaterisa.weatherapp.model.WeatherForecast
import com.amaterisa.weatherapp.model.WeatherForecastResponse
import com.amaterisa.weatherapp.network.asDatabaseModel
import com.amaterisa.weatherapp.utils.WeatherTypeConverters

@Entity
data class DatabaseWeatherForecastResponse constructor(
    @PrimaryKey (autoGenerate = true)
    val id: Long?,
    @TypeConverters(WeatherTypeConverters::class)
    val list: List<WeatherForecast>,
    @TypeConverters(WeatherTypeConverters::class)
    val city: City?
)

fun DatabaseWeatherForecastResponse.asDomainModel(): WeatherForecastResponse {
    return WeatherForecastResponse(
        list = this.list,
        city = this.city,
        id = this.id)
}

fun List<DatabaseWeatherForecastResponse>.asDomainModel(): List<WeatherForecastResponse> {
    return map {
        WeatherForecastResponse(
            id = it.id,
            list = it.list,
            city = it.city
        )
    }
}