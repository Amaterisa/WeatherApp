package com.amaterisa.weatherapp.weather

import android.app.Application
import androidx.lifecycle.*
import com.amaterisa.weatherapp.network.*
import com.amaterisa.weatherapp.network.model.WeatherForecastResponse
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