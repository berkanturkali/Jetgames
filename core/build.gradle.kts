import ProjectLib.common
import ProjectLib.remote

plugins {
    androidLibrary
    kotlinAndroid
    kotlin(kotlinKapt)
    daggerHilt
    id("com.google.secrets_gradle_plugin") version "0.6.1"
    parcelize
}

android {
    compileSdk = Config.Version.compileSdkVersion

    defaultConfig {
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = Config.useSupportLibrary
        }
        javaCompileOptions {
            annotationProcessorOptions {
                arguments += Pair("room.incremental", "true")
            }
        }
        buildConfigField("int", "databaseVersion", 1.toString())
        buildConfigField("String", "BASE_URL", "\"https://api.rawg.io/api/\"")
    }

    buildTypes {
        named(BuildType.DEBUG) {
            isMinifyEnabled = false
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
        named(BuildType.RELEASE) {
            isMinifyEnabled = true
            proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = composeVersion
    }
    packagingOptions {
        resources {
            excludes.add("/META-INF/{AL2.0,LGPL2.1}")
        }
    }
}

dependencies {
    //project lib
    implementation(project(remote))
    implementation(project(common))

    //network
    implementation(Dependencies.Network.retrofit)
    implementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Network.moshi)
    testImplementation(Dependencies.Network.retrofitMoshi)
    testImplementation(Dependencies.Test.mockWebServer)


    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)

    //pagination
    implementation(Dependencies.Pagination.pagingCompose)

    //coil
    implementation(Dependencies.Coil.coil)

    //timber
    implementation(Dependencies.Logger.timber)

    //navigation
    implementation(Dependencies.Navigation.navigation)
    implementation(Dependencies.Accompanist.navigation)

    //cache
    implementation(Dependencies.Room.room)
    implementation(Dependencies.Room.roomKtx)
    kapt(Dependencies.Room.AnnotationProcessor.room)


    implementation("androidx.core:core-ktx:1.7.0")
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
    androidTestImplementation("androidx.test.ext:junit:1.1.3")
    androidTestImplementation("androidx.test.espresso:espresso-core:3.4.0")
    testImplementation(Dependencies.Test.truth)
    testImplementation(Dependencies.Test.mockk)
}