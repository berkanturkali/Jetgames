package extensions

import BuildType.Companion.Debug
import BuildType.Companion.Release
import Config
import Version
import com.android.build.gradle.AppExtension
import org.gradle.api.JavaVersion

val ProjectExtension.Companion.AndroidApp: ProjectExtension
    get() = AndroidAppExtension()

private class AndroidAppExtension : ProjectExtension {
    override val name: String
        get() = "android"

    override fun configure(extension: Any) {
        if (extension !is AppExtension) return
        extension.apply {
            compileSdkVersion(Config.compileSdkVersion)
            defaultConfig {
                applicationId = Config.applicationId
                minSdk = Config.minSdkVersion
                targetSdk = Config.targetSdkVersion
                versionCode(Config.versionCode)
                versionName(Config.versionName)
                multiDexEnabled = Config.isMultiDexEnabled
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
            composeOptions {
                kotlinCompilerExtensionVersion = Version.compose
            }
            packagingOptions {
                resources {
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
            }
            buildFeatures.apply {
                compose = true
            }
        }
    }
}