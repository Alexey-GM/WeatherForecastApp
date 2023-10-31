package com.example.weatherforecastapp.domain.model

import java.io.Serializable

data class Weather(
    val city: String,
    val date: String,
    val averageTemp: Int,
    val main: String,
    val details: List<WeatherDetails>
): Serializable