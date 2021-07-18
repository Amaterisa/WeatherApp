package com.amaterisa.weatherapp.weather

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.amaterisa.weatherapp.R
import com.amaterisa.weatherapp.databinding.WeatherFragmentBinding
import com.amaterisa.weatherapp.network.LONDON
import com.amaterisa.weatherapp.network.MANAUS
import com.amaterisa.weatherapp.network.SEOUL

class WeatherFragment : Fragment() {

    private val viewModel: WeatherViewModel by lazy {
        val activity = requireNotNull(this.activity) {
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

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        inflater.inflate(R.menu.city_menu, menu)
        super.onCreateOptionsMenu(menu, inflater)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        viewModel.updateFilter(
            when (item.itemId) {
                R.id.manaus -> MANAUS
                R.id.london -> LONDON
                else -> SEOUL
            })
        return true
    }
}

