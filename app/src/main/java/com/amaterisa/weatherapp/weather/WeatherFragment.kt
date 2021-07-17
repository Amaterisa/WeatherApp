package com.amaterisa.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.amaterisa.weatherapp.weather.WeatherViewModel
import com.amaterisa.weatherapp.databinding.WeatherFragmentBinding

class WeatherFragment : Fragment() {
//    private lateinit var viewModel: WeatherViewModel

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

//        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}