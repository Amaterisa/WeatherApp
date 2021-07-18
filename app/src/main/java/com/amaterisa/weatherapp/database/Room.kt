package com.amaterisa.weatherapp.database

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.room.*
import com.amaterisa.weatherapp.utils.WeatherTypeConverters

@Dao
interface WeatherForecastResponseDao {
    @Query("select * from databaseweatherforecastresponse")
    fun getWeathers(): LiveData<List<DatabaseWeatherForecastResponse>>

    @Query("select * from databaseweatherforecastresponse order by id DESC limit 1")
    suspend fun getCurrentWeather(): DatabaseWeatherForecastResponse?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(vararg weathers: DatabaseWeatherForecastResponse)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(vararg weather: DatabaseWeatherForecastResponse)
}

@Database(entities = [DatabaseWeatherForecastResponse::class], version = 1)
@TypeConverters(WeatherTypeConverters::class,)
abstract class WeatherForecastResponsesDatabase : RoomDatabase() {
    abstract val weatherForecastResponseDao: WeatherForecastResponseDao
}

private lateinit var INSTANCE: WeatherForecastResponsesDatabase

fun getDatabase(context: Context): WeatherForecastResponsesDatabase {
    synchronized(WeatherForecastResponsesDatabase::class.java) {
        if (!::INSTANCE.isInitialized) {
            INSTANCE = Room.databaseBuilder(context.applicationContext,
                WeatherForecastResponsesDatabase::class.java,
                "weathers")
                .build()
        }
    }
    return INSTANCE
}