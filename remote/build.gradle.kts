plugins {
    kotlinLib
}

dependencies {
    implementation(
        libs.moshi,
        libs.retrofit,
        libs.retrofit.moshi,
        libs.logging.interceptor,
        libs.okhttp
    )
}
