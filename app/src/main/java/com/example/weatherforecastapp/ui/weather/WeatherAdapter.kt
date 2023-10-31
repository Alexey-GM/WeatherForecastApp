package com.example.weatherforecastapp.ui.weather

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.weatherforecastapp.R
import com.example.weatherforecastapp.databinding.ItemWeatherColdBinding
import com.example.weatherforecastapp.databinding.ItemWeatherHotBinding
import com.example.weatherforecastapp.domain.model.Weather
import com.example.weatherforecastapp.utils.getDayOfWeek

private const val COLD_WEATHER = 0
private const val HOT_WEATHER = 1

class WeatherItemDiffCallback : DiffUtil.ItemCallback<Weather>() {
    override fun areItemsTheSame(
        oldItem: Weather, newItem: Weather
    ): Boolean = oldItem == newItem

    override fun areContentsTheSame(
        oldItem: Weather, newItem: Weather
    ): Boolean = oldItem == newItem

}

class WeatherAdapter(private val onViewClick: (Weather) -> Unit) : ListAdapter<Weather, RecyclerView.ViewHolder>(WeatherItemDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return if (viewType == HOT_WEATHER) {
            val binding =
                ItemWeatherHotBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            WeatherViewHolderHot(binding, onViewClick)
        } else {
            val binding =
                ItemWeatherColdBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            WeatherViewHolderCold(binding, onViewClick)
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (getItemViewType(position) == HOT_WEATHER) {
            (holder as WeatherViewHolderHot).bind(getItem(position))
        } else {
            (holder as WeatherViewHolderCold).bind(getItem(position))
        }
    }

    override fun getItemViewType(position: Int): Int {
        return if (getItem(position).averageTemp >= 5) HOT_WEATHER else COLD_WEATHER
    }
}

class WeatherViewHolderHot(
    private val binding: ItemWeatherHotBinding,
    private val onViewClick: (Weather) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemWeather: Weather) {
        val temp = itemWeather.averageTemp
        binding.tvTemperature.text = binding.root.context.getString(R.string.celsius, temp)
        binding.tvDateTime.text = itemWeather.date
        binding.tvDayOfWeek.text = getDayOfWeek(itemWeather.date)
        binding.tvMain.text = itemWeather.main
        binding.btView.setOnClickListener {
            onViewClick(itemWeather)
        }
    }
}

class WeatherViewHolderCold(
    private val binding: ItemWeatherColdBinding,
    private val onViewClick: (Weather) -> Unit
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(itemWeather: Weather) {
        val temp = itemWeather.averageTemp
        binding.tvTemperature.text = binding.root.context.getString(R.string.celsius, temp)
        binding.tvDateTime.text = itemWeather.date
        binding.tvDayOfWeek.text = getDayOfWeek(itemWeather.date)
        binding.tvMain.text = itemWeather.main
        binding.btView.setOnClickListener {
            onViewClick(itemWeather)
        }
    }
}