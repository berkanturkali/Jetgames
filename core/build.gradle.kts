
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

android.sourceSets {
    val sharedTestDir = "src/sharedTest/java"
    val androidTest by getting
    val test by getting
    androidTest.java.srcDirs(sharedTestDir)
    test.java.srcDirs(sharedTestDir)
}

dependencies {
    // project lib
    implementation(project(remote))

    // network
    implementation(Library.retrofit, Library.moshi)
    testImplementation(Library.moshi, Library.retrofitMoshi, Library.mockWebServer)

    // hilt
    implementation(Library.daggerHiltAndroid)
    kapt(Library.daggerHiltCompiler)

    // pagination
    implementation(Library.pagingCompose)

    // coil
    implementation(Library.coil)

    // navigation
    implementation(Library.navigation, Library.navComponent)

    // cache
    implementation(Library.room)
    implementation(Library.roomKtx)
    kapt(Library.roomCompiler)
    testImplementation(Library.truth)
    testImplementation(Library.mockk)
    androidTestImplementation(Library.truth)

    testImplementation(Library.coroutines)
}
