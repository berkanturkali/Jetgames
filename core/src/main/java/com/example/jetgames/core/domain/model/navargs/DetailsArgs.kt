package com.example.jetgames.core.domain.model.navargs

import android.os.Parcelable
import androidx.compose.runtime.Immutable
import kotlinx.parcelize.Parcelize

@Immutable
@Parcelize
data class DetailsArgs(
    val id: Int,
    val screenshots: List<String?>? = null,
) : Parcelable