package extensions

import BuildType.Companion.Debug
import BuildType.Companion.Release
import Config
import com.android.build.gradle.LibraryExtension
import org.gradle.api.JavaVersion
import org.gradle.api.plugins.ExtensionAware


val ProjectExtension.Companion.AndroidLib: ProjectExtension
    get() = AndroidLibExtension()

private class AndroidLibExtension : ProjectExtension {

    override val name: String get() = "android"

    override fun configure(extension: Any) {
        if (extension !is LibraryExtension) return
        extension.apply {
            compileSdk = Config.compileSdkVersion
            defaultConfig {
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
                testInstrumentationRunner = Config.testInstrumentationRunner
                vectorDrawables {
                    useSupportLibrary = Config.useSupportLibrary
                }
            }

            buildTypes {
                named(Debug.name) {
                    isMinifyEnabled = Debug.isMinifyEnabled
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
                named(Release.name) {
                    isMinifyEnabled = Release.isMinifyEnabled
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_11
                targetCompatibility = JavaVersion.VERSION_11
            }

            buildFeatures {
                compose = true
                buildConfig = true
            }
            composeOptions {
                kotlinCompilerExtensionVersion = "1.2.0-alpha04"
            }
            packagingOptions {
                resources {
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
            }
        }
    }
}

