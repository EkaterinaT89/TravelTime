package ru.wb.treveltime.flights.domain.repository

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.wb.treveltime.flights.data.repositoryImpl.FlightRepositoryImpl
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    @Singleton
    abstract fun bindFlightRepository(impl: FlightRepositoryImpl): FlightRepository
}