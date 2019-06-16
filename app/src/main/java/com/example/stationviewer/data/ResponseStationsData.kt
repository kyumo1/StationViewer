package com.example.stationviewer.data

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ResponseStationsData(
    @Json(name = "line_cd")
    val lineCd: Int,
    @Json(name = "line_name")
    val lineName: String,
    @Json(name = "line_lon")
    val lineLon: Double,
    @Json(name = "line_lat")
    val lineLat: Double,
    @Json(name = "line_zoom")
    val lineZoom: Int,
    @Json(name = "station_l")
    val stationL: List<ResponseStationData>

)