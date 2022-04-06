package com.example.jetgames.core.domain.model.navargs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class DetailsArgs(
    val id: Int,
    val screenshots: List<String?>? = null,
) : Parcelable
