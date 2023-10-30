package com.example.weatherforecastapp.domain.model

data class Weather(
    val city: String,
    val date: String,
    val averageTemp: Int,
    val details: List<WeatherDetails>
)