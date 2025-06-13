import ProjectLib.core

plugins {
    androidLib
}

android{
    defaultConfig {
        namespace = "com.example.jetgames.common"
    }
}

dependencies {
    implementation(project(core))
    // compose
    implementation(
        libs.compose.ui,
        libs.compose.material,
        libs.core.ktx,
        libs.compose.runtime,
    )

    // lottie
    implementation(libs.lottie)

    // palette
    implementation(libs.palette)

    // coil
    implementation(libs.coil)

    // accompanist placeholder
    implementation(libs.accompanist.placeholder)
}
