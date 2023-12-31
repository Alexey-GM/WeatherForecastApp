package com.example.weatherforecastapp.ui.weather

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherforecastapp.domain.WeatherRepository
import com.example.weatherforecastapp.domain.model.Weather
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

private const val INITIAL_CITY = "Kemerovo"

@HiltViewModel
class WeatherViewModel @Inject constructor(private val weatherRepository: WeatherRepository) :
    ViewModel() {
    private val _weather: MutableLiveData<List<Weather>> = MutableLiveData()
    val weather: LiveData<List<Weather>> get() = _weather

    init {
        loadWeather(INITIAL_CITY)
    }

    fun loadWeather(city: String) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val data = weatherRepository.getWeather(city)
                _weather.postValue(data)
            } catch (_: Throwable) {
            }
        }
    }
}
