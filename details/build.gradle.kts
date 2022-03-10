import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
    daggerHilt
}

android {
    compileSdk = Config.compileSdkVersion
}

dependencies {
    implementation(project(common), project(core))

    //coil
    implementation(Library.coil)

    //compose
    implementation(
        Library.composeMaterial,
        Library.pager,
        Library.pagerIndicator,
        Library.composeUiUtil)

    //hilt
    implementation(Library.daggerHiltAndroid)
    kapt(Library.daggerHiltCompiler)

    implementation(Library.lifecycleKtx)
    implementation(Library.composeLiveData)

    implementation(Library.hiltNavigation)
}