import ProjectLib.common
import ProjectLib.core
import ProjectLib.details
import ProjectLib.filter
import ProjectLib.home
import ProjectLib.remote

plugins {
    androidApplication
    kotlinAndroid
    kotlin(kotlinKapt)
    daggerHilt
}

android {
    compileSdk = Config.Version.compileSdkVersion

    defaultConfig {
        applicationId = Config.Android.applicationId
        minSdk = Config.Version.minSdkVersion
        targetSdk = Config.Version.targetSdkVersion
        versionCode = Config.Version.versionCode
        versionName = Config.Version.versionName
        multiDexEnabled = Config.isMultiDexEnabled
        testInstrumentationRunner = Config.Android.testInstrumentationRunner
        vectorDrawables {
            useSupportLibrary = Config.useSupportLibrary
        }
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
    kapt{
        correctErrorTypes = true
    }
}
hilt {
    enableAggregatingTask = true
}

dependencies {
    implementation(project(common))
    implementation(project(core))
    implementation(project(remote))
    implementation(project(home))
    implementation(project(details))
    implementation(project(filter))
    implementAll(Dependencies.AndroidX.components)
    implementAll(Dependencies.Compose.components)
    implementation(Dependencies.Compose.activity)
    testImplementation(Dependencies.Test.junit)
    androidTestImplementation(Dependencies.Test.junitExt)
    androidTestImplementation(Dependencies.Test.espresso)
    androidTestImplementation(Dependencies.Test.composeJunit)
    debugImplementation(Dependencies.Test.composeUiTooling)

    //navigation
    implementation(Dependencies.Navigation.navigation)
    implementation(Dependencies.Accompanist.navigation)

    //animation
    implementation(Dependencies.Accompanist.animations)

    //hilt
    implementation(Dependencies.DI.daggerHiltAndroid)
    kapt(Dependencies.DI.AnnotationProcessor.daggerHiltCompiler)

    //coil
    implementation(Dependencies.Coil.coil)

    //moshi
    implementation(Dependencies.Network.moshi)

    debugImplementation(Dependencies.Performance.leakCanary)
}