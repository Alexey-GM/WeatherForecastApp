package com.example.weatherforecastapp

import android.app.Application
import com.example.weatherforecastapp.data.WeatherRepositoryImpl
import com.example.weatherforecastapp.data.nw.WeatherAPIService
import com.example.weatherforecastapp.domain.WeatherRepository

class App : Application() {
    private lateinit var apiService: WeatherAPIService
    companion object {
        lateinit var repository: WeatherRepository
            private set
    }

    override fun onCreate() {
        super.onCreate()
        initApiService()
        initRepository()
    }

    private fun initApiService() {
        apiService = WeatherAPIService.createApiService()
    }

    private fun initRepository() {
        repository = WeatherRepositoryImpl(apiService)
    }
}