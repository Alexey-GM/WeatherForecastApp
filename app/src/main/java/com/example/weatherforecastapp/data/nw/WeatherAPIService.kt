package com.example.weatherforecastapp.data.nw

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Query

private const val BASE_URL = "https://api.openweathermap.org/"

interface WeatherAPIService {
    companion object {
        private val logger: HttpLoggingInterceptor =
            HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)
        private val okHttp =
            OkHttpClient.Builder()
                .addInterceptor(logger)
                .build()

        fun createApiService(): WeatherAPIService {
            val retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttp)
                .build()
            return retrofit.create(WeatherAPIService::class.java)
        }
    }

    @GET("/data/2.5/forecast?")
    suspend fun getWeatherList(
        @Query("appid") appId: String,
        @Query("units") units: String,
        @Query("lang") language: String,
        @Query("q") city: String,
    ): WeatherNW
}