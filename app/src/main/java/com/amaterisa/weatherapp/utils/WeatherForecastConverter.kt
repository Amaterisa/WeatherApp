package com.amaterisa.weatherapp.utils

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.amaterisa.weatherapp.network.model.City
import com.amaterisa.weatherapp.network.model.WeatherForecast
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class WeatherTypeConverters() {
    var gson = Gson()

    @TypeConverter
    fun weatherForecastToString(weatherForecastItems: List<WeatherForecast>): String {
        return gson.toJson(weatherForecastItems)
    }

    @TypeConverter
    fun stringToWeatherForecast(data: String): List<WeatherForecast> {
        val listType = object : TypeToken<List<WeatherForecast>>() {
        }.type
        return gson.fromJson(data, listType)
    }

    @TypeConverter
    fun cityToString(city: City): String {
        return gson.toJson(city)
    }

    @TypeConverter
    fun stringToCity(data: String): City {
        val listType = object : TypeToken<City>() {
        }.type
        return gson.fromJson(data, listType)
    }
}