package ru.wb.treveltime.activity.repository

import ru.wb.treveltime.activity.dto.Flight

interface FlightRepository {

    suspend fun getAll(): List<Flight>

}