package com.example.weatherforecastapp.domain.model

data class Weather(
    val city: String,
    val temperature: Double,
    val dateTime: String,
    val pressure: Int,
    val iconLink: String
)