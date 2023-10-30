package com.example.weatherforecastapp.domain

import com.example.weatherforecastapp.domain.model.Weather

interface WeatherRepository {
    fun getWeather(): List<Weather>
}