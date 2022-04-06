package com.example.jetgames.core.domain.model.navargs

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Immutable
@Parcelize
data class ScreenshotsArgs(
    val screenshots: List<String?>,
    val selectedPage: Int = 0
) : Parcelable
