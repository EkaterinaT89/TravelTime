package ru.wb.treveltime.flights.presentation.viewModel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import ru.wb.treveltime.flights.data.dataModel.Flight
import ru.wb.treveltime.flights.domain.useCase.LikeUseCase
import javax.inject.Inject

@HiltViewModel
class FlightDetailsViewModel @Inject constructor() : ViewModel() {

    private val likeUseCase = LikeUseCase()

    fun like(flight: Flight) {
        likeUseCase.like(flight)
    }

}