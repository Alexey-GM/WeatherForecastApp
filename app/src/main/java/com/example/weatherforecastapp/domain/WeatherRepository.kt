package com.example.weatherforecastapp.domain

import com.example.weatherforecastapp.domain.model.Weather

interface WeatherRepository {
    suspend fun getWeather(city: String): List<Weather>
}