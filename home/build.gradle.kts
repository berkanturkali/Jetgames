
import ProjectLib.common
import ProjectLib.core

plugins {
    androidLib
    daggerHilt
}
dependencies {
    implementation(project(common))
    implementation(project(core))

    // hilt
    implementation(Library.daggerHiltAndroid)
    kapt(Library.daggerHiltCompiler)

    // androidx
    implementation(Library.lifecycle)
    implementation(Library.lifecycleKtx)

    // pagination
    implementation(Library.pagingCompose)

    // compose
    implementation(Library.composeMaterial)
    implementation(Library.materialIcons)
    implementation(Library.constraintLayout)

    // accompanist
    implementation(Library.swipeRefresh)
    implementation(Library.placeholder)

    // coil
    implementation(Library.coil)

    implementation(Library.hiltNavigation)
}
