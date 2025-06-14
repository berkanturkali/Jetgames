plugins {
    androidLib
    alias(libs.plugins.kotlin.serialization)
}

android {
    namespace = "com.example.jetgames.navigation"
}

dependencies {

    //kotlin serialization
    implementation(libs.kotlinx.serialization.json)
}