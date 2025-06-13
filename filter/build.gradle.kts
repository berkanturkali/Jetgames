import ProjectLib.common
import ProjectLib.core

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
    implementation(project(common))
    implementation(project(core))
    // compose
    implementation(
        libs.compose.ui,
        libs.compose.material,
        libs.constraint.layout.compose,
        libs.compose.runtime,
    )

    // navigation
    implementation(libs.accompanist.navigation)

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
