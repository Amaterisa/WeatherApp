package com.amaterisa.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.net.toUri
import com.amaterisa.weatherapp.R
import com.amaterisa.weatherapp.databinding.WeatherFragmentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class WeatherFragment : Fragment() {
    private lateinit var viewModel: WeatherViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = WeatherFragmentBinding.inflate(inflater)

        viewModel = ViewModelProvider(requireActivity()).get(WeatherViewModel::class.java)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        return binding.root
    }
}