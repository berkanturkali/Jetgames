import ProjectLib.common
import ProjectLib.core
import ProjectLib.details
import ProjectLib.favorites
import ProjectLib.filter
import ProjectLib.home
import ProjectLib.navigation
import ProjectLib.remote

plugins {
    androidApp
    alias(libs.plugins.kotlin.serialization)
}

android {
    kapt {
        correctErrorTypes = true
    }
}
hilt {
    enableAggregatingTask = true
}

dependencies {
    // project libs
    implementAllProjects(
        common,
        core,
        remote,
        home,
        details,
        filter,
        favorites,
        navigation
    )

    // compose
    implementation(
        libs.compose.material,
        libs.compose.ui,
        libs.compose.livedata,
        libs.constraint.layout.compose,
        libs.compose.ui.util
    )

    debugImplementation(libs.compose.ui.tooling)

    // navigation
    implementation(libs.navigation.compose, libs.accompanist.navigation)

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.hilt.navigation.compose)
    kapt(libs.hilt.compiler)

    // coil
    implementation(libs.coil)

    // moshi
    implementation(libs.moshi)

    // accompanist
    implementation(libs.accompanist.insets, libs.accompanist.animations)

    // leak canary
    debugImplementation(libs.leakcanary)

    // timber
    implementation(libs.timber)

    //kotlin serialization
    implementation(libs.kotlinx.serialization.json)
}