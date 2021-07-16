package com.amaterisa.weatherapp.network

import com.amaterisa.weatherapp.network.model.WeatherProperty
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"
const val API_KEY = "b17b525ac44b891abf93bd977453713f"
const val UNITS = "metric"
const val MANAUS_LAT: Double = -3.10719
const val MANAUS_LON: Double = -60.0261

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .build()

interface WeatherApiService {
    @GET("data/2.5/weather?")
    suspend fun getProperty(@Query("lat") lat: Double,
                            @Query("lon") lon: Double,
                            @Query("units") units: String,
                            @Query("APPID") appId: String): WeatherProperty
}

object WeatherApi {
    val retrofitService : WeatherApiService by lazy { retrofit.create(WeatherApiService::class.java) }
}