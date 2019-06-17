package com.example.stationviewer.usecases

import com.example.stationviewer.data.StationOutputData
import io.reactivex.Observable

interface IGetStationsUseCase {
    fun get(lineCode: Int): Observable<List<StationOutputData>>
}