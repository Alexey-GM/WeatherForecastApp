package com.example.weatherforecastapp.data

import com.example.weatherforecastapp.data.nw.WeatherAPIService
import com.example.weatherforecastapp.domain.WeatherRepository
import com.example.weatherforecastapp.domain.model.Weather

private const val CITY = "Kemerovo"
private const val APP_ID = "625b6fd5c5be3e9c809460446a1fd3e9"
private const val UNITS = "metric"
private const val LANGUAGE = "ru"
private const val OK_CODE = "200"

class WeatherRepositoryImpl(private val api: WeatherAPIService) : WeatherRepository {
    override suspend fun getWeather(): List<Weather> {
        return try {
            val result = api.getWeatherList(
                appId = APP_ID,
                units = UNITS,
                language = LANGUAGE,
                city = CITY
            )
            result.list.map { it.toDomain(result.city) }
        } catch (error: Throwable) {
            emptyList<Weather>()
        }
    }
}