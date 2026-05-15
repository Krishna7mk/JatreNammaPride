package com.example.jatrenammapride.model

import androidx.annotation.Keep

@Keep
data class Event(

    val id: String = "",

    val title: String = "",

    val time: String = "",

    val location: String = "",

    val description: String = "",

    val isLive: Boolean = false
)