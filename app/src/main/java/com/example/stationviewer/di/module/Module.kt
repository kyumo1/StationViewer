package com.example.stationviewer.di.module

import com.example.stationviewer.repositories.IStationsRepository
import com.example.stationviewer.repositories.StationsRepository
import com.example.stationviewer.usecases.GetStationsUseCase
import com.example.stationviewer.usecases.IGetStationsUseCase
import com.example.stationviewer.viewmodels.StationsViewModel
import dagger.Module
import dagger.Provides

@Module
class Module() {
    @Provides
    fun provideIStationsViewModel(getStationsUseCase: IGetStationsUseCase): StationsViewModel {
        return StationsViewModel(getStationsUseCase)
    }

    @Provides
    fun provideIGetStationsUseCase(stationsRepository: IStationsRepository): IGetStationsUseCase {
        return GetStationsUseCase(stationsRepository)
    }

    @Provides
    fun provideIStationsRepository(): IStationsRepository {
        return StationsRepository()
    }
}