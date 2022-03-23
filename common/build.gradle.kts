import ProjectLib.core

plugins {
    androidLib
}

android {
    compileSdk = Config.compileSdkVersion
}

dependencies {
    implementation(project(core))
    //compose
    api(Library.composeUiPreview)
    api(Library.composeUiTooling)
    implementation(Library.composeUi, Library.composeMaterial, Library.coreKtx)


    //lottie
    implementation(Library.lottie)

    //Timber
    api(Library.timber)

    //palette
    implementation(Library.palette)

    //coil
    implementation(Library.coil)

    implementation(Library.placeholder)
}