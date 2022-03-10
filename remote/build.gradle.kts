plugins {
    kotlinLib
}
dependencies {
    implementation(Library.moshi,
        Library.retrofit,
        Library.retrofitMoshi,
        Library.loggingInterceptor,
        Library.okhttp)
}