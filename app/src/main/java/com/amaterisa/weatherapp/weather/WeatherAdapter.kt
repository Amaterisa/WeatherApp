package com.amaterisa.weatherapp.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.amaterisa.weatherapp.R
import com.amaterisa.weatherapp.databinding.WeatherItemBinding
import com.amaterisa.weatherapp.model.WeatherForecast

class WeatherAdapter : RecyclerView.Adapter<WeatherViewHolder>() {
    var weathers: List<WeatherForecast> = emptyList()
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        val withDataBinding: WeatherItemBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            WeatherViewHolder.LAYOUT,
            parent,
            false)
        return WeatherViewHolder(withDataBinding)
    }

    override fun getItemCount() = weathers.size

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
        holder.viewDataBinding.weather = weathers[position]
    }
}

class WeatherViewHolder(val viewDataBinding: WeatherItemBinding) :
    RecyclerView.ViewHolder(viewDataBinding.root) {
    companion object {
        @LayoutRes
        val LAYOUT = R.layout.weather_item
    }
}