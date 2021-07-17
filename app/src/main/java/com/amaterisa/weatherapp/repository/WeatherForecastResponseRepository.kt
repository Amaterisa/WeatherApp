package com.amaterisa.weatherapp.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import com.amaterisa.weatherapp.database.WeatherForecastResponsesDatabase
import com.amaterisa.weatherapp.database.asDomainModel
//import com.amaterisa.weatherapp.database.asDomainModel
import com.amaterisa.weatherapp.network.model.WeatherForecastResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class WeatherForecastResponseRepository(private val database: WeatherForecastResponsesDatabase) {

    /**
     * A playlist of videos that can be shown on the screen.
     */
    val weathers: LiveData<List<WeatherForecastResponse>> =
        Transformations.map(database.weatherForecastResponseDao().getWeathers()) {
            it.asDomainModel()
        }

    /**
     * Refresh the videos stored in the offline cache.
     *
     * This function uses the IO dispatcher to ensure the database insert database operation
     * happens on the IO dispatcher. By switching to the IO dispatcher using `withContext` this
     * function is now safe to call from any thread including the Main thread.
     *
     * To actually load the videos for use, observe [videos]
     */
//    suspend fun refreshVideos() {
//        withContext(Dispatchers.IO) {
//            val playlist = Network.devbytes.getPlaylist().await()
//            database.videoDao.insertAll(*playlist.asDatabaseModel())
//        }
//    }
}