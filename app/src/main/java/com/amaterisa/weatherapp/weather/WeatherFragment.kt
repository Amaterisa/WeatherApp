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

        viewModel.property.observe(viewLifecycleOwner, { newProperty ->
            showImage(binding.icon, newProperty.weather[0].icon)
        })

        return binding.root
    }

    private fun showImage(imgView: ImageView, imgUrl: String){
        val url = "http://openweathermap.org/img/w/${imgUrl}.png"
        val imgUri = url.toUri().buildUpon().scheme("https").build()
        Glide.with(this)
            .load(imgUri)
            .apply(
                RequestOptions()
//                .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image))
            .into(imgView)
    }
}