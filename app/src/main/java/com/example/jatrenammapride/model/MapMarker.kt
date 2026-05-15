package com.example.jatrenammapride.model

import androidx.annotation.Keep

@Keep
data class MapMarker(

    val id: String = "",

    val title: String = "",

    val latitude: Double = 0.0,

    val longitude: Double = 0.0,

    val category: String = ""
)