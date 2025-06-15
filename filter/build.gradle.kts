import ProjectLib.common
import ProjectLib.core
import ProjectLib.navigation

plugins {
    androidLib
    daggerHilt
}

android{
    defaultConfig {
        namespace = "com.example.jetgames.filter"
    }
}


dependencies {

    implementAllProjects(
        core,
        common,
        navigation
    )
    // compose
    implementation(
        libs.compose.ui,
        libs.compose.material,
        libs.constraint.layout.compose,
        libs.compose.runtime,
    )

    // hilt
    implementation(libs.hilt.android)
    implementation(libs.androidx.lifecycle.livedata)
    kapt(libs.hilt.compiler)
    implementation(libs.hilt.navigation.compose)

    // livedata-ktx
    implementation(libs.lifecycle.livedata.ktx)
    implementation(libs.compose.livedata)

    // coroutines-android
    implementation(libs.coroutines.android)
}
