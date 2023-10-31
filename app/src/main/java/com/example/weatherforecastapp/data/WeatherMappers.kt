package com.example.weatherforecastapp.data

import com.example.weatherforecastapp.data.nw.WeatherNW
import com.example.weatherforecastapp.domain.model.Weather
import com.example.weatherforecastapp.domain.model.WeatherDetails

fun WeatherNW.toDomain(): List<Weather> {
    val city = this.city.name
    val weatherDetailsMap = this.list.groupBy { it.dtTxt.split(" ")[0] } // Группировка по датам

    return weatherDetailsMap.map { (date, weatherNWList) ->
        val averageTemp = weatherNWList.map { it.main.temp.toInt() }.average().toInt()
        val weatherDetailsList = weatherNWList.map { weatherNW ->
            val datetime = weatherNW.dtTxt
            val temp = weatherNW.main.temp.toInt()
            val feelsLike = weatherNW.main.feelsLike.toInt()
            val humidity = weatherNW.main.humidity
            val pressure = weatherNW.main.pressure
            val wind = weatherNW.wind.speed
            val main = weatherNW.weather.firstOrNull()?.main ?: ""
            val description = weatherNW.weather.firstOrNull()?.description ?: ""
            WeatherDetails(datetime, temp, feelsLike, humidity, pressure, wind, main, description)
        }

        Weather(city, date, averageTemp, weatherDetailsList.first().main, weatherDetailsList)
    }
}