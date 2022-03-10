package extensions

import com.android.build.gradle.AppExtension
import BuildType.Companion.Debug
import BuildType.Companion.Release
import org.gradle.api.JavaVersion
import Version

val ProjectExtension.Companion.AndroidApp: ProjectExtension
    get() = AndroidAppExtension()

private class AndroidAppExtension:ProjectExtension{
    override val name: String
        get() = "android"

    override fun configure(extension: Any) {
        if(extension !is AppExtension) return
        extension.apply {
            defaultConfig{
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
                    isMinifyEnabled = false
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
                named(Release.name) {
                    isMinifyEnabled = true
                    proguardFiles("proguard-android-optimize.txt", "proguard-rules.pro")
                }
            }

            compileOptions {
                sourceCompatibility = JavaVersion.VERSION_1_8
                targetCompatibility = JavaVersion.VERSION_1_8
            }
            composeOptions {
                kotlinCompilerExtensionVersion = Version.compose
            }
            packagingOptions {
                resources {
                    excludes.add("/META-INF/{AL2.0,LGPL2.1}")
                }
            }
        }
    }
}