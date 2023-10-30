package com.example.weatherforecastapp.data

import com.example.weatherforecastapp.data.nw.WeatherNW
import com.example.weatherforecastapp.domain.model.Weather

private const val ICON_LINK_PATTERN = "https://openweathermap.org/img/w/"
private const val ICON_LINK_FORMAT = ".png"
fun WeatherNW.Weather.toDomain(city: WeatherNW.City) =
    Weather(
        city = city.name,
        temperature = main.temp,
        pressure = main.pressure,
        dateTime = dtTxt,
        iconLink = ICON_LINK_PATTERN + weather.first().icon + ICON_LINK_FORMAT
    )