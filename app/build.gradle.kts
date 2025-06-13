import ProjectLib.common
import ProjectLib.core
import ProjectLib.details
import ProjectLib.favorites
import ProjectLib.filter
import ProjectLib.home
import ProjectLib.remote

plugins {
    androidApp
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
    implementation(project(common))
    implementation(project(core))
    implementation(project(remote))
    implementation(project(home))
    implementation(project(details))
    implementation(project(filter))
    implementation(project(favorites))

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
}