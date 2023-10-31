package com.example.weatherforecastapp.utils

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

fun getDayOfWeek(date: String): String {
    try {
        val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.US)
        val dateResult = sdf.parse(date)!!

        val calendar = Calendar.getInstance()
        calendar.time = dateResult

        val today = Calendar.getInstance()
        today.time = Date()

        val tomorrow = Calendar.getInstance()
        tomorrow.time = Date()
        tomorrow.add(Calendar.DAY_OF_YEAR, 1)

        return when {
            calendar[Calendar.YEAR] == today[Calendar.YEAR] &&
                    calendar[Calendar.DAY_OF_YEAR] == today[Calendar.DAY_OF_YEAR] -> "Today"
            calendar[Calendar.YEAR] == tomorrow[Calendar.YEAR] &&
                    calendar[Calendar.DAY_OF_YEAR] == tomorrow[Calendar.DAY_OF_YEAR] -> "Tomorrow"
            else -> {
                val sdfDayOfWeek = SimpleDateFormat("EEEE", Locale.US)
                sdfDayOfWeek.format(dateResult)
            }
        }
    } catch (e: ParseException) {
        e.printStackTrace()
        return "Ошибка в формате даты"
    }
}