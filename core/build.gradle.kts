import ProjectLib.common
import ProjectLib.remote

plugins {
    androidLib
    daggerHilt
    id("com.google.android.libraries.mapsplatform.secrets-gradle-plugin")
    parcelize
}
android.defaultConfig.javaCompileOptions {
    annotationProcessorOptions {
        arguments += Pair("room.incremental", "true")
    }
}
android.defaultConfig.buildConfigField("int", "databaseVersion", 1.toString())
android.defaultConfig.buildConfigField("String", "databaseName", "\"jetgames_db\"")
android.defaultConfig.buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")

android{
    defaultConfig {
        namespace = "com.example.jetgames.core"
    }
}

dependencies {
    // project lib
    implementation(project(remote))

    // network
    implementation(libs.retrofit, libs.moshi)
    testImplementation(libs.moshi, libs.retrofit.moshi, libs.mockwebserver)

    // hilt
    implementation(libs.hilt.android)
    kapt(libs.hilt.compiler)

    // pagination
    implementation(libs.paging.compose)

    // coil
    implementation(libs.coil)

    // navigation
    implementation(libs.accompanist.navigation, libs.navigation.compose)

    // cache
    implementation(libs.room.runtime)
    implementation(libs.room.ktx)
    kapt(libs.room.compiler)

    testImplementation(libs.truth)
    testImplementation(libs.mockk)
    androidTestImplementation(libs.truth)

    testImplementation(libs.coroutines.test)
}
