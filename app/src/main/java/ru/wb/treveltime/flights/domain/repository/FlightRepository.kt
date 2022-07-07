package ru.wb.treveltime.flights.domain.repository

import ru.wb.treveltime.flights.data.dataModel.Flight
import kotlinx.coroutines.flow.Flow

interface FlightRepository {

    val data: Flow<List<Flight>>

    suspend fun getAll()

}