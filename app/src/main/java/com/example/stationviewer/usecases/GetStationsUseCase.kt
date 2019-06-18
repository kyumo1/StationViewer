package com.example.stationviewer.usecases

import com.example.stationviewer.data.StationOutputData
import com.example.stationviewer.repositories.IStationsRepository

import io.reactivex.Observable
import javax.inject.Inject

class GetStationsUseCase @Inject constructor(private val stationsRepository: IStationsRepository): IGetStationsUseCase {
    override fun get(lineCode: Int): Observable<List<StationOutputData>> {
        return stationsRepository.get(lineCode).map { data -> data.map { s -> StationOutputData(s.code, s.name, s.longitude, s.latitude) } }
    }
}