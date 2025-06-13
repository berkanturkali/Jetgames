
import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
    daggerHilt
}

android{
    defaultConfig {
        namespace = "com.example.jetgames.home"
    }
}

dependencies {
    implementation(project(common))
    implementation(project(core))

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // androidx
    implementation(libs.lifecycle.runtime.ktx)
    implementation(libs.lifecycle.viewmodel.ktx)

    // pagination
    implementation(libs.paging.compose)

    // compose
    implementation(libs.compose.material)
    implementation(libs.compose.icons)
    implementation(libs.constraint.layout.compose)
    implementation(libs.compose.runtime)

    // accompanist
    implementation(libs.accompanist.swipe)
    implementation(libs.accompanist.placeholder)

    // coil
    implementation(libs.coil)

    // hilt-navigation
    implementation(libs.hilt.navigation.compose)
}
