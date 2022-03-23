import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
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
}