package com.amaterisa.weatherapp.network

import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Deferred
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import com.jakewharton.retrofit2.adapter.kotlin.coroutines.CoroutineCallAdapterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"
const val API_KEY = "b17b525ac44b891abf93bd977453713f"
const val UNITS = "metric"
const val MANAUS_LAT: Double = -3.107
const val MANAUS_LON: Double = -60.02
const val MANAUS: String = "manaus,br"
const val LONDON: String = "london,uk"
const val SEOUL: String = "seoul,kr"

private val moshi = Moshi.Builder()
    .add(KotlinJsonAdapterFactory())
    .build()

private val retrofit = Retrofit.Builder()
    .addConverterFactory(MoshiConverterFactory.create(moshi))
    .baseUrl(BASE_URL)
    .addCallAdapterFactory(CoroutineCallAdapterFactory())
    .build()

interface WeatherApiService {
    @GET("data/2.5/forecast?")
    fun getWeather(@Query("lat") lat: Double,
                       @Query("lon") lon: Double,
                       @Query("units") units: String,
                       @Query("APPID") appId: String): Deferred<NetworkWeatherForecastResponse>

    @GET("data/2.5/forecast?")
    fun getWeather(@Query("q") city: String,
                   @Query("units") units: String,
                   @Query("APPID") appId: String): Deferred<NetworkWeatherForecastResponse>
}

object WeatherApi {
    val retrofitService = retrofit.create(WeatherApiService::class.java)
}