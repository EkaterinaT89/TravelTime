package ru.wb.treveltime.flights.domain.useCase

import ru.wb.treveltime.flights.data.dataModel.Flight

class LikeUseCase {

    fun like(flight: Flight) {
        flight.liked = !flight.liked
    }

}