import ProjectLib.common
import ProjectLib.core
import ProjectLib.navigation

plugins {
    androidLib
    daggerHilt
}

android{
    defaultConfig {
        namespace = "com.example.jetgames.favorites"
    }
}

dependencies {

    implementAllProjects(
        core,
        common,
        navigation
    )

    // compose
    implementation(libs.compose.material)
    implementation(libs.compose.runtime)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    implementation(libs.hilt.navigation.compose)

    // coil
    implementation(libs.coil)
}
