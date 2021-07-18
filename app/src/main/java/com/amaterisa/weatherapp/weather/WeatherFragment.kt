package com.amaterisa.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.amaterisa.weatherapp.R
import com.amaterisa.weatherapp.databinding.WeatherFragmentBinding
import com.amaterisa.weatherapp.databinding.WeatherItemBinding
import com.amaterisa.weatherapp.model.WeatherForecast

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {
        val activity = requireNotNull(this.activity) {
            "You can only access the viewModel after onViewCreated()"
        }
        ViewModelProvider(this, WeatherViewModel.Factory(activity.application)).get(WeatherViewModel::class.java)
    }

    private var viewModelAdapter: WeatherAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?): View? {

        val binding = WeatherFragmentBinding.inflate(inflater)

        binding.lifecycleOwner = this

        binding.viewModel = viewModel

        viewModel.weather.observe(viewLifecycleOwner, { newWeather ->
            if (newWeather != null){
                binding.weather = newWeather
                viewModelAdapter?.weathers = newWeather.list
            }
        })

        viewModelAdapter = WeatherAdapter()

        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            adapter = viewModelAdapter
        }

        return binding.root
    }
}

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