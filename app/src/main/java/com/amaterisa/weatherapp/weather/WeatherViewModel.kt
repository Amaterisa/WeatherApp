package com.amaterisa.weatherapp.weather

import android.app.Application
import android.util.Log
import androidx.lifecycle.*
import com.amaterisa.weatherapp.database.getDatabase
import com.amaterisa.weatherapp.model.WeatherForecastResponse
import com.amaterisa.weatherapp.repository.WeatherForecastResponseRepository
import kotlinx.coroutines.launch

enum class WeatherApiStatus { LOADING, ERROR, DONE }

class WeatherViewModel(application: Application): AndroidViewModel(application) {
    private val _status = MutableLiveData<WeatherApiStatus>()

    val status: LiveData<WeatherApiStatus>
        get() = _status

    private val database = getDatabase(application)
    private val weathersRepository = WeatherForecastResponseRepository(database)

    init {
        viewModelScope.launch {
            _status.value = WeatherApiStatus.LOADING
            try {
//                weathersRepository.refreshWeather()
                _status.value = WeatherApiStatus.DONE
            } catch (e: Exception) {
                _status.value = WeatherApiStatus.ERROR
            }
        }
    }

    val weather = weathersRepository.currentWeather

    class Factory(val app: Application) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(WeatherViewModel::class.java)) {
                @Suppress("UNCHECKED_CAST")
                return WeatherViewModel(app) as T
            }
            throw IllegalArgumentException("Unable to construct viewmodel")
        }
    }
}