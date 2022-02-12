plugins {
    androidLibrary
    kotlinAndroid
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
    api(Dependencies.Compose.composeUiPreview)
    api(Dependencies.Compose.composeUiTooling)
    implementation(Dependencies.AndroidX.coreKtx)
    implementation(Dependencies.Compose.composeUi)
    implementation(Dependencies.Compose.composeMaterial)

    //Timber
    api(Dependencies.Logger.timber)
    implementation("androidx.appcompat:appcompat:1.4.1")
    implementation("com.google.android.material:material:1.5.0")
    testImplementation("junit:junit:4.13.2")
}