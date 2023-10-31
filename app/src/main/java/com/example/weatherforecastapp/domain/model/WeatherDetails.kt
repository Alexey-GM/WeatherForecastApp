package com.example.weatherforecastapp.domain.model

data class WeatherDetails(
    val datetime: String,
    val temp: Int,
    val feelsLike: Int,
    val humidity: Int,
    val pressure: Int,
    val wind: Double,
    val main: String,
    val description: String,
)