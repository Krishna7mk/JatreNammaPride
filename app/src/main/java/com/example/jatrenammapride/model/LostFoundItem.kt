package com.example.jatrenammapride.model

import androidx.annotation.Keep

@Keep
data class LostFoundItem(

    val id: String = "",

    val title: String = "",

    val description: String = "",

    val imageUrl: String = "",

    val resolved: Boolean = false
)