plugins {
    androidLib
}

android {
    compileSdk = Config.compileSdkVersion
}

dependencies {
    //compose
    api(Library.composeUiPreview)
    api(Library.composeUiTooling)
    implementation(Library.composeUi, Library.composeMaterial, Library.coreKtx)


    //lottie
    implementation(Library.lottie)

    //Timber
    api(Library.timber)
}