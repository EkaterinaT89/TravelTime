package ru.wb.treveltime.flights.data.repositoryImpl

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asFlow
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.flowOn
import ru.wb.treveltime.flights.data.dataModel.Flight
import ru.wb.treveltime.flights.data.network.ApiService
import ru.wb.treveltime.flights.domain.errors.ApiException
import ru.wb.treveltime.flights.domain.errors.NetWorkException
import ru.wb.treveltime.flights.domain.errors.UnknownException
import ru.wb.treveltime.flights.domain.repository.FlightRepository
import java.io.IOException
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class FlightRepositoryImpl @Inject constructor(
    private val apiService: ApiService
) : FlightRepository {

    private val listData = mutableListOf<Flight>()

    private val _flights = MutableLiveData<List<Flight>>()
    val flights: MutableLiveData<List<Flight>>
        get() = _flights

    override val data = _flights.asFlow().flowOn(Dispatchers.Default)

    override suspend fun getAll() {
        try {
            val response = apiService.getAll()
            val flights =
                response.body()?.data ?: throw ApiException(response.code(), response.message())
            for (flight in flights) {
                listData.add(flight)
            }
            _flights.value = listData
        } catch (e: IOException) {
            throw NetWorkException
        } catch (e: Exception) {
            throw UnknownException
        }
    }

}