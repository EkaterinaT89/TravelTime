package ru.wb.treveltime.flights.presentation.viewModel

import androidx.lifecycle.*
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.wb.treveltime.flights.data.dataModel.Flight
import ru.wb.treveltime.flights.presentation.model.FeedModelState
import ru.wb.treveltime.flights.domain.repository.FlightRepository
import ru.wb.treveltime.flights.domain.useCase.LikeUseCase
import ru.wb.treveltime.flights.domain.utils.ActionType
import java.lang.Exception
import javax.inject.Inject

@HiltViewModel
class FlightViewModel @Inject constructor(
    private val repository: FlightRepository
) : ViewModel() {

    private val likeUseCase = LikeUseCase()

    private val _dataState = MutableLiveData<FeedModelState>()
    val dataState: LiveData<FeedModelState>
        get() = _dataState

    private var lastAction: ActionType? = null

    val data: LiveData<List<Flight>> = repository.data.asLiveData(Dispatchers.Default)

    private var currentFlight: Flight? = null

    init {
        getAll()
    }

    fun tryAgain() {
        when (lastAction) {
            ActionType.GETALL -> retryGetAll()
            ActionType.LIKE -> retryLike()
            else -> getAll()
        }
    }

    fun like(flight: Flight) {
        likeUseCase.like(flight)
        currentFlight = flight
    }

    private fun retryLike() {
        currentFlight?.let {
            like(it)
        }
    }

    fun getAll() {
        viewModelScope.launch {
            lastAction = ActionType.GETALL
            try {
                _dataState.postValue(FeedModelState(loading = true))
                repository.getAll()
                _dataState.value = FeedModelState()
            } catch (e: Exception) {
                _dataState.value = FeedModelState(error = true)
            }
        }
    }

    private fun retryGetAll() {
        getAll()
    }

}