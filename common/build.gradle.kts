import ProjectLib.core

plugins {
    androidLib
}
dependencies {
    implementation(project(core))
    // compose
    implementation(Library.composeUi, Library.composeMaterial, Library.coreKtx)

    // lottie
    implementation(Library.lottie)

    // palette
    implementation(Library.palette)

    // coil
    implementation(Library.coil)

    implementation(Library.placeholder)
}
