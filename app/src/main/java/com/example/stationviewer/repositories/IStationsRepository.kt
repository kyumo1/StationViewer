package com.example.stationviewer.repositories

import com.example.stationviewer.data.StationData
import io.reactivex.Observable

interface IStationsRepository {
    fun get(lineCode: Int): Observable<List<StationData>>
}