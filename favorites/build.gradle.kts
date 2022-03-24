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
    implementation(project(common))
    implementation(project(core))

    //compose
    implementation(
        Library.composeMaterial
    )

    //hilt
    implementation(Library.daggerHiltAndroid)
    kapt(Library.daggerHiltCompiler)

    implementation(Library.hiltNavigation)

    //coil
    implementation(Library.coil)
}