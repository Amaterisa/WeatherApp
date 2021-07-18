package com.amaterisa.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.amaterisa.weatherapp.weather.WeatherViewModel
import com.amaterisa.weatherapp.databinding.WeatherFragmentBinding
import com.amaterisa.weatherapp.model.WeatherForecastResponse

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, WeatherViewModel.Factory(activity.application)).get(WeatherViewModel::class.java)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = WeatherFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.weather.observe(viewLifecycleOwner, { newWeather ->
            if (newWeather != null){
                binding.weather = newWeather
            }
        })

        return binding.root
    }
}