package com.example.stationviewer.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseStationData(
    @Json(name = "station_cd")
    val stationCd: Int,
    @Json(name = "station_g_cd")
    val stationGCd: Int,
    @Json(name = "station_name")
    val stationName: String,
    val lon: Double,
    val lat: Double
)