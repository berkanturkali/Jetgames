package com.example.jetgames.core.domain.model.navargs

import com.example.jetgames.core.domain.model.navargs.base.BaseNavType

class ScreenshotsNavType : BaseNavType<ScreenshotsArgs>(isNullableAllowed = false) {

    override fun parseValue(value: String): ScreenshotsArgs {
        return moshi.parseValue(value)
    }
}
