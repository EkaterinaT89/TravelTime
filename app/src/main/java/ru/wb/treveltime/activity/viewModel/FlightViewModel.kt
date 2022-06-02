package ru.wb.treveltime.activity.viewModel

import androidx.lifecycle.*
import kotlinx.coroutines.launch
import ru.wb.treveltime.activity.dto.Flight
import ru.wb.treveltime.activity.errors.AppError
import ru.wb.treveltime.activity.model.FeedModelState
import ru.wb.treveltime.activity.repository.FlightRepository
import ru.wb.treveltime.activity.repository.FlightRepositoryImpl
import ru.wb.treveltime.activity.service.ActionType
import java.lang.Exception

class FlightViewModel() : ViewModel() {

    private val repository: FlightRepository = FlightRepositoryImpl()

    private val _dataState = MutableLiveData<FeedModelState>()
    val dataState: LiveData<FeedModelState>
        get() = _dataState

    var lastAction: ActionType? = null

    val listData = mutableListOf<Flight>()

    private val _listFlights = MutableLiveData<List<Flight>>()
    val listFlights: LiveData<List<Flight>> = _listFlights

    init {
        getAll()
    }

    fun tryAgain() {
        when (lastAction) {
            ActionType.GETALL -> retryGetAll()
            else -> getAll()
        }
    }

    fun getAll() = viewModelScope.launch {
        lastAction = ActionType.GETALL
        try {
            _dataState.postValue(FeedModelState(loading = true))
            val flights = repository.getAll()
            _dataState.value = FeedModelState()
            for (flight in flights) {
                listData.add(flight)
            }
            _listFlights.value = listData
        } catch (e: Exception) {
            _dataState.value = FeedModelState(error = true)
        }
    }

    fun retryGetAll() {
        getAll()
    }

}