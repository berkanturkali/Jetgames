import ProjectLib.common
import ProjectLib.core

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
    implementation(project(common))
    implementation(project(core))

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
