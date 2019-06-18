package com.example.stationviewer.repositories

import com.example.stationviewer.data.ResponseStationsData
import com.example.stationviewer.data.StationData
import com.github.kittinunf.fuel.httpGet
import com.github.kittinunf.result.Result
import com.squareup.moshi.Moshi
import io.reactivex.Observable

class StationsRepository : IStationsRepository {

    override fun get(lineCode: Int): Observable<List<StationData>> {
        return Observable.create { emitter ->
            val requestUrl = "http://www.ekidata.jp/api/l/$lineCode.json"
            requestUrl.httpGet().header("Connection", "close").responseString { result ->
                when (result) {
                    is Result.Success -> {
                        val adapter = Moshi.Builder().build().adapter(ResponseStationsData::class.java)
                        val json = result.value.split("\n")[2].split("=")[1].trim()
                        val responseData = adapter.fromJson(json) as ResponseStationsData
                        val stationLists =
                            responseData.stationL.map { s -> StationData(s.stationCd, s.stationName, s.lat, s.lon) }

                        emitter.onNext(stationLists)
                        emitter.onComplete()
                    }
                    is Result.Failure -> {
                        println("connection error")
                        emitter.onError(result.getException())
                    }
                }
            }
        }
    }
}