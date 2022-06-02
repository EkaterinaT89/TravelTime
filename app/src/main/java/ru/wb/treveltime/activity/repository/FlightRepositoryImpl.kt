package ru.wb.treveltime.activity.repository

import ru.wb.treveltime.activity.api.ApiService
import ru.wb.treveltime.activity.dto.Flight
import ru.wb.treveltime.activity.errors.NetWorkException
import ru.wb.treveltime.activity.errors.UnknownException
import java.io.IOException

class FlightRepositoryImpl() : FlightRepository {

    override suspend fun getAll(): List<Flight> {
        try {
            return ApiService.FlightsApi.retrofitService.getAll().data
        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

}