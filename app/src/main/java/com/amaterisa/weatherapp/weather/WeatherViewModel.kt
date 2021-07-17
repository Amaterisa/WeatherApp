package com.amaterisa.weatherapp.weather

import android.app.Application
import androidx.lifecycle.*
import com.amaterisa.weatherapp.database.getDatabase
import com.amaterisa.weatherapp.network.*
import com.amaterisa.weatherapp.network.model.WeatherForecastResponse
import com.amaterisa.weatherapp.repository.WeatherForecastResponseRepository
import kotlinx.coroutines.launch

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class WeatherViewModel(application: Application): AndroidViewModel(application) {
    private val _status = MutableLiveData<WeatherApiStatus>()

    val status: LiveData<WeatherApiStatus>
        get() = _status

    private val _property = MutableLiveData<WeatherForecastResponse>()

    val forecastResponse: LiveData<WeatherForecastResponse>
        get() = _property

    init {
        getWeatherProperty(MANAUS_LAT, MANAUS_LON)
    }

    private val database = getDatabase(application)
    private val weathersRepository = WeatherForecastResponseRepository(database)

    /**
     * init{} is called immediately when this ViewModel is created.
     */
//    init {
//        viewModelScope.launch {
//            weathersRepository.refreshVideos()
//        }
//    }

//    val playlist = videosRepository.videos

    /**
     */
    /**
     * Factory for constructing DevByteViewModel with parameter
     */
    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }

    private fun getWeatherProperty(lat: Double, lon: Double) {
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING
            try {
                _property.value = WeatherApi.retrofitService.getProperty(lat, lon, UNITS, API_KEY)
                _status.value = WeatherApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
            }
        }
    }
}