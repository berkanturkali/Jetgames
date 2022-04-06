package com.example.jetgames.core.domain.model.navargs

import com.example.jetgames.core.domain.model.navargs.base.BaseNavType

class DetailsNavType : BaseNavType<DetailsArgs>(isNullableAllowed = false) {

    override fun parseValue(value: String): DetailsArgs {
        return moshi.parseValue(value)
    }
}
