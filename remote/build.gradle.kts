plugins {
    kotlinLibrary
    id("org.jetbrains.kotlin.jvm")
    kotlin(kotlinKapt)

}
dependencies{
    implementAll(Dependencies.Network.components)
    implementation(Dependencies.DI.hiltCore)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHilt)
}