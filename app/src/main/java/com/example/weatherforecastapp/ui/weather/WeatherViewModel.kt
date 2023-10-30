package com.example.weatherforecastapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.domain.WeatherRepository
import com.example.weatherforecastapp.domain.model.Weather
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class WeatherViewModel(private val weatherRepository: WeatherRepository) : ViewModel() {
    private val _weather: MutableLiveData<List<Weather>> = MutableLiveData()
    val weather: LiveData<List<Weather>> get() = _weather

    init {
        loadWeather()
    }

    private fun loadWeather() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = weatherRepository.getWeather()
                _weather.postValue(data)
            } catch (_: Throwable) {
            }
        }
    }
}

@Suppress("UNCHECKED_CAST")
class MainViewModelFactory(
    private val weatherRepository: WeatherRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        val viewModel = WeatherViewModel(weatherRepository)
        return viewModel as T
    }
}