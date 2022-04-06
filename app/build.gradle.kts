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
        Library.composeMaterial,
        Library.composeUi,
        Library.composeLiveData,
        Library.constraintLayout,
        Library.composeUiUtil
    )

    debugImplementation(Library.composeUiTooling)

    // navigation
    implementation(Library.navComponent, Library.navigation)

    // hilt
    implementation(Library.daggerHiltAndroid)
    implementation(Library.hiltNavigation)
    kapt(Library.daggerHiltCompiler)

    // coil
    implementation(Library.coil)

    // moshi
    implementation(Library.moshi)

    // accompanist
    implementation(Library.insets, Library.animations)

    // leak canary
    debugImplementation(Library.leakCanary)

    implementation(Library.timber)
}