import Dependencies.Accompanist.Version.animationsVersion
import Dependencies.Accompanist.Version.navigationVersion
import Dependencies.Accompanist.Version.pagerIndicatorVersion
import Dependencies.Accompanist.Version.pagerVersion
import Dependencies.Accompanist.Version.placeholderVersion
import Dependencies.Accompanist.Version.swipeRefreshVersion
import Dependencies.Compose.Version.util
import Dependencies.Lottie.Version.lottieVersion

const val kotlinAndroid: String = "android"
const val kotlinKapt: String = "kapt"
const val ktLintVersion: String = "0.43.0"
const val kotlinVersion = "1.5.31"
const val composeVersion = "1.0.5"

object Config {
    object Version {
        const val minSdkVersion = 21
        const val compileSdkVersion = 31
        const val targetSdkVersion = 31
        const val versionName = "1.0"
        const val versionCode = 1
    }

    const val isMultiDexEnabled = true
    const val useSupportLibrary = true

    object Android {
        const val applicationId = "com.example.jetgames"
        const val testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
}

interface Libraries {
    val components: List<String>
}

object Dependencies {
    object Compose : Libraries {
        object Version {
            const val activityVersion = "1.4.0"
            const val composeLiveData = "1.2.0-alpha02"
            const val constraintLayoutVersion = "1.0.0"
            const val util = "1.2.0.alpha-04"
        }

        const val composeUiUtil = "androidx.compose.ui:ui-util:$util"
        const val activity = "androidx.activity:activity-compose:${Version.activityVersion}"
        const val composeUiPreview = "androidx.compose.ui:ui-tooling-preview:$composeVersion"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${composeVersion}"
        const val composeMaterial = "androidx.compose.material:material:$composeVersion"
        const val composeUi = "androidx.compose.ui:ui:$composeVersion"
        const val materialIcons =
            "androidx.compose.material:material-icons-extended:$composeVersion"

        const val composeLiveData =
            "androidx.compose.runtime:runtime-livedata:${Version.composeLiveData}"
        const val constraintLayout =
            "androidx.constraintlayout:constraintlayout-compose:${Version.constraintLayoutVersion}"

        override val components: List<String>
            get() = listOf(composeMaterial, composeUi, composeUiPreview)

    }

    object AndroidX : Libraries {
        object Version {
            const val coreKtx = "1.7.0"
            const val lifecycle = "2.4.0"
        }

        const val coreKtx = "androidx.core:core-ktx:${Version.coreKtx}"
        const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Version.lifecycle}"
        const val lifecycleKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.lifecycle}"

        override val components: List<String>
            get() = listOf(coreKtx, lifecycle)

    }

    object Network : Libraries {
        object Version {
            const val okhttp: String = "4.9.0"
            const val retrofit: String = "2.9.0"
            const val moshi: String = "1.13.0"
        }

        object AnnotationProcessor {
            const val moshi = "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshi}"
        }

        const val okhttp: String = "com.squareup.okhttp3:okhttp:${Version.okhttp}"
        const val loggingInterceptor: String =
            "com.squareup.okhttp3:logging-interceptor:${Version.okhttp}"
        const val retrofit: String = "com.squareup.retrofit2:retrofit:${Version.retrofit}"
        const val retrofitMoshi: String =
            "com.squareup.retrofit2:converter-moshi:${Version.retrofit}"
        const val moshi: String = "com.squareup.moshi:moshi-kotlin:${Version.moshi}"

        override val components: List<String>
            get() = listOf(okhttp, loggingInterceptor, retrofit, retrofitMoshi, moshi)
    }

    object DI {
        object Version {
            const val daggerHilt = "2.38.1"
            const val androidXHilt = "1.0.0-alpha02"
            const val hiltNavigationComposeVersion = "1.0.0-alpha03"
        }

        object AnnotationProcessor {
            const val daggerHiltCompiler = "com.google.dagger:hilt-compiler:${Version.daggerHilt}"
            const val androidxHiltCompiler: String =
                "androidx.hilt:hilt-compiler:${Version.androidXHilt}"
        }

        const val daggerHiltAndroid: String =
            "com.google.dagger:hilt-android:${Version.daggerHilt}"
        const val hiltViewModel: String =
            "androidx.hilt:hilt-lifecycle-viewmodel:${Version.androidXHilt}"
        const val hiltTesting: String =
            "com.google.dagger:hilt-android-testing:${Version.daggerHilt}"
        const val hiltCore: String = "com.google.dagger:hilt-core:${Version.daggerHilt}"
        const val hiltNavigation =
            "androidx.hilt:hilt-navigation-compose:${Version.hiltNavigationComposeVersion}"
    }

    object Coroutines {
        object Version {
            const val coroutines: String = "1.6.0"
        }

        const val core: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutines}"
        const val androidCoroutines: String =
            "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutines}"

    }

    object Pagination {
        object Version {
            const val pagingCompose = "1.0.0-alpha14"
        }

        const val pagingCompose = "androidx.paging:paging-compose:${Version.pagingCompose}"
    }

    object Performance {
        object Version {
            const val leakCanary = "2.8.1"
        }

        const val leakCanary =
            "com.squareup.leakcanary:leakcanary-android:${Version.leakCanary}"
    }


    object Coil {
        object Version {
            const val coil = "1.4.0"
        }

        const val coil = "io.coil-kt:coil-compose:${Version.coil}"
    }

    object Palette {
        object Version {
            const val paletteVersion = "1.0.0"
        }

        const val palette = "androidx.palette:palette:${Version.paletteVersion}"
    }

    object Accompanist {
        object Version {
            const val pagerVersion = "0.22.1-rc"
            const val pagerIndicatorVersion = "0.24.2-alpha"
            const val placeholderVersion = "0.24.1-alpha"
            const val animationsVersion = "0.20.3"
            const val swipeRefreshVersion = "0.24.1-alpha"
            const val navigationVersion = "0.24.1-alpha"
        }

        const val pager = "com.google.accompanist:accompanist-pager:$pagerVersion"
        const val pagerIndicator =
            "com.google.accompanist:accompanist-pager-indicators:$pagerIndicatorVersion"
        const val placeholder =
            "com.google.accompanist:accompanist-placeholder-material:$placeholderVersion"
        const val animations =
            "com.google.accompanist:accompanist-navigation-animation:$animationsVersion"
        const val swipeRefresh =
            "com.google.accompanist:accompanist-swiperefresh:$swipeRefreshVersion"
        const val navigation =
            "com.google.accompanist:accompanist-navigation-material:$navigationVersion"
    }

    object Lottie {
        object Version {
            const val lottieVersion = "4.2.1"
        }

        const val lottie = "com.airbnb.android:lottie-compose:$lottieVersion"
    }

    object Navigation {
        object Version {
            const val navigationVersion = "2.4.1"
        }

        const val navigation = "androidx.navigation:navigation-compose:${Version.navigationVersion}"
    }

    object Logger {
        object Version {
            const val timber = "4.7.1"
        }

        const val timber = "com.jakewharton.timber:timber:${Version.timber}"
    }

    object Test {
        object Version {
            const val junit = "4.13"
            const val junitExt = "1.1.3"
            const val espresso = "3.4.0"
            const val truth = "1.1.3"
            const val mockWebServer = "4.9.3"
            const val mockk = "1.12.2"
        }

        const val junit = "junit:junit:${Version.junit}"
        const val junitExt = "androidx.test.ext:junit:${Version.junitExt}"
        const val composeJunit = "androidx.compose.ui:ui-test-junit4:${composeVersion}"
        const val composeUiTooling = "androidx.compose.ui:ui-tooling:${composeVersion}"
        const val espresso = "androidx.test.espresso:espresso-core:${Version.espresso}"
        const val truth = "com.google.truth:truth:${Version.truth}"
        const val mockWebServer = "com.squareup.okhttp3:mockwebserver:${Version.mockWebServer}"
        const val mockk = "io.mockk:mockk:${Version.mockk}"
    }

}

object ProjectLib {
    const val app: String = ":app"
    const val common: String = ":common"
    const val remote: String = ":remote"
    const val core: String = ":core"
    const val home: String = ":home"
    const val details: String = ":details"
}

