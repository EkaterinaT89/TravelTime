package ru.wb.treveltime.activity.dto

import com.google.gson.annotations.SerializedName

class FlightsAll {
    @SerializedName("data")
    val data: List<Flight> = emptyList()
}