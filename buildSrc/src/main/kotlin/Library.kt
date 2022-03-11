object Library {

    /* Ktx */
    const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
    const val lifecycleKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"
    const val liveDataKtx = "androidx.lifecycle:lifecycle-livedata-ktx:${Version.liveDataKtx}"

    /* compose */
    const val composeUiUtil = "androidx.compose.ui:ui-util:${Version.util}"
    const val activity = "androidx.activity:activity-compose:${Version.activity}"
    const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:${Version.compose}"
    const val composeUiTooling = "androidx.compose.ui:ui-tooling:${Version.compose}"
    const val composeMaterial = "androidx.compose.material:material:${Version.compose}"
    const val composeUi = "androidx.compose.ui:ui:${Version.compose}"
    const val materialIcons =
        "androidx.compose.material:material-icons-extended:${Version.compose}"

    const val composeLiveData =
        "androidx.compose.runtime:runtime-livedata:${Version.composeLiveData}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout-compose:${Version.constraintLayout}"

    /* network */
    const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
    const val loggingInterceptor: String =
        "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
    const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
    const val retrofitMoshi: String =
        "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
    const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"


    /* hilt */
    const val daggerHiltAndroid: String =
        "com.google.dagger:hilt-android:${Version.daggerHilt}"
    const val hiltCore: String = "com.google.dagger:hilt-core:${Version.daggerHilt}"
    const val hiltNavigation =
        "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationComposeVersion}"
    const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
    const val androidxHiltCompiler: String =
        "androidx.hilt:hilt-compiler:${Version.androidXHilt}"

    /* paging-3 */
    const val pagingCompose = "androidx.paging:paging-compose:${Version.pagingCompose}"

    /* coroutines */
    const val core: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
    const val androidCoroutines: String =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    /* leak-canary */
    const val leakCanary =
        "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"

    /* coil */
    const val coil = "io.coil-kt:coil-compose:${Version.coil}"

    /* palette */
    const val palette = "androidx.palette:palette:${Version.palette}"

    /* accompanist */
    const val pager = "com.google.accompanist:accompanist-pager:${Version.pager}"
    const val pagerIndicator =
        "com.google.accompanist:accompanist-pager-indicators:${Version.pagerIndicator}"
    const val placeholder =
        "com.google.accompanist:accompanist-placeholder-material:${Version.placeholder}"
    const val animations =
        "com.google.accompanist:accompanist-navigation-animation:${Version.animations}"
    const val swipeRefresh =
        "com.google.accompanist:accompanist-swiperefresh:${Version.swipeRefresh}"
    const val navigation =
        "com.google.accompanist:accompanist-navigation-material:${Version.navigation}"

    /* lottie */
    const val lottie = "com.airbnb.android:lottie-compose:${Version.lottie}"


    /* nav-component */
    const val navComponent = "androidx.navigation:navigation-compose:${Version.navComponent}"

    /* timber */
    const val timber = "com.jakewharton.timber:timber:${Version.timber}"

    /* room */
    const val roomCompiler = "androidx.room:room-compiler:${Version.room}"
    const val room = "androidx.room:room-runtime:${Version.room}"
    const val roomKtx = "androidx.room:room-ktx:${Version.room}"

    /* test */
    const val junit = "junit:junit:${Version.junit}"
    const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
    const val composeJunit = "androidx.compose.ui:ui-test-junit4:${Version.compose}"
    const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
    const val truth = "com.google.truth:truth:${Version.truth}"
    const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
    const val mockk = "io.mockk:mockk:${Version.mockk}"
    const val coroutines =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutines}"
}