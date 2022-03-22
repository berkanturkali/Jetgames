import ProjectLib.common
import ProjectLib.core
import ProjectLib.details
import ProjectLib.filter
import ProjectLib.home
import ProjectLib.remote

plugins {
    androidApp
}

android {
    compileSdk = Config.compileSdkVersion

    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    kapt {
        correctErrorTypes = true
    }
}
hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(project(common))
    implementation(project(core))
    implementation(project(remote))
    implementation(project(home))
    implementation(project(details))
    implementation(project(filter))
    implementation(
        Library.composeUiPreview,
        Library.composeUiTooling,
        Library.composeMaterial,
        Library.composeUi,
        Library.composeLiveData,
        Library.constraintLayout)

    debugImplementation(Library.composeUiTooling)

    //navigation
    implementation(Library.navComponent, Library.navigation)

    //animation
    implementation(Library.animations)

    //hilt
    implementation(Library.daggerHiltAndroid)
    implementation(Library.hiltNavigation)
    kapt(Library.daggerHiltCompiler)

    //coil
    implementation(Library.coil)

    //moshi
    implementation(Library.moshi)

    //accompanist
    implementation(Library.insets)

    debugImplementation(Library.leakCanary)
}