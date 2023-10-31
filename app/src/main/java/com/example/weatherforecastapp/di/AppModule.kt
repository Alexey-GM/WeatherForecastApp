package com.example.weatherforecastapp.di

import com.example.weatherforecastapp.data.WeatherRepositoryImpl
import com.example.weatherforecastapp.data.nw.WeatherAPIService
import com.example.weatherforecastapp.domain.WeatherRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideApi(): WeatherAPIService{
        return WeatherAPIService.createApiService()
    }

    @Provides
    @Singleton
    fun provideRepository(
        api: WeatherAPIService
    ): WeatherRepository {
        return WeatherRepositoryImpl(api)
    }
}