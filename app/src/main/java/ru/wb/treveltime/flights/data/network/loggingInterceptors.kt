package ru.wb.treveltime.flights.data.network

import okhttp3.logging.HttpLoggingInterceptor
import ru.wb.treveltime.BuildConfig

fun loggingInterceptor() = HttpLoggingInterceptor()
    .apply {
        if (BuildConfig.DEBUG) {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }