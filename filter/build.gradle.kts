import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
    daggerHilt
}

dependencies {
    implementation(project(common))
    implementation(project(core))
    // compose
    implementation(
        Library.composeUi,
        Library.composeMaterial,
        Library.constraintLayout
    )

    // navigation
    implementation(Library.navigation)

    // hilt
    implementation(Library.daggerHiltAndroid)
    kapt(Library.daggerHiltCompiler)
    implementation(Library.hiltNavigation)

    // livedata-ktx
    implementation(Library.liveDataKtx)
    implementation(Library.composeLiveData)

    implementation(Library.androidCoroutines)
}
