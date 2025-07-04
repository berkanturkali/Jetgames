import ProjectLib.common
import ProjectLib.core
import ProjectLib.navigation

plugins {
    androidLib
    daggerHilt
}

android {
    defaultConfig {
        namespace = "com.example.jetgames.details"
    }
}

dependencies {

    implementAllProjects(
        common,
        core,
        navigation
    )
    // coil
    implementation(libs.coil)

    // compose
    implementation(
        libs.compose.material,
        libs.accompanist.pager,
        libs.accompanist.pager.indicators,
        libs.compose.ui.util,
        libs.compose.runtime,
    )

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.lifecycle.viewmodel.ktx)
    implementation(libs.compose.livedata)

    implementation(libs.hilt.navigation.compose)
}
