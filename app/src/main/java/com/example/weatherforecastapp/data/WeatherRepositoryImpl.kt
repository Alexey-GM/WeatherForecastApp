package com.example.weatherforecastapp.data

import com.example.weatherforecastapp.data.nw.WeatherAPIService
import com.example.weatherforecastapp.domain.WeatherRepository
import com.example.weatherforecastapp.domain.model.Weather

private const val APP_ID = "625b6fd5c5be3e9c809460446a1fd3e9"
private const val UNITS = "metric"
private const val LANGUAGE = "en"

class WeatherRepositoryImpl(private val api: WeatherAPIService) : WeatherRepository {
    override suspend fun getWeather(city: String): List<Weather> {
        return try {
            val result = api.getWeatherList(
                appId = APP_ID,
                units = UNITS,
                language = LANGUAGE,
                city = city
            )
            result.toDomain()
        } catch (error: Throwable) {
            emptyList()
        }
    }
}