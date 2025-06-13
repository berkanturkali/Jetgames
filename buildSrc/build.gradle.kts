import Build_gradle.Plugin.androidLib
import Build_gradle.Plugin.app
import Build_gradle.Plugin.kotlinLib
import Build_gradle.Plugin.ktlintPlugin

plugins {
    `kotlin-dsl`
}
gradlePlugin {
    plugins {
        register(app) {
            id = app
            implementationClass = "plugin.ApplicationPlugin"
        }

        register(androidLib) {
            id = androidLib
            implementationClass = "plugin.AndroidLibraryPlugin"
        }

        register(kotlinLib) {
            id = kotlinLib
            implementationClass = "plugin.KotlinLibraryPlugin"
        }

        register(ktlintPlugin) {
            id = ktlintPlugin
            implementationClass = "plugin.KtlintPlugin"
        }
    }
}

repositories {
    google()
    mavenCentral()
    maven("https://plugins.gradle.org/m2/")
    gradlePluginPortal()
}
object Plugin {
    const val app: String = "app"
    const val androidLib: String = "androidLibrary"
    const val kotlinLib: String = "kotlinLibrary"
    const val ktlintPlugin: String = "ktlint"

    object Version {
        const val kotlin: String = "2.1.0"
        const val androidGradle: String = "8.6.0"
        const val daggerHiltAndroid: String = "2.52"
        const val secretsGradle = "2.0.1"
        const val ktlint = "10.2.1"
        const val composeCompiler = "2.1.0"
    }

    const val kotlin: String = "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}"
    const val androidGradle: String = "com.android.tools.build:gradle:${Version.androidGradle}"
    const val daggerHilt: String =
        "com.google.dagger:hilt-android-gradle-plugin:${Version.daggerHiltAndroid}"
    const val secretsGradle: String =
        "com.google.android.libraries.mapsplatform.secrets-gradle-plugin:secrets-gradle-plugin:${Version.secretsGradle}"
    const val ktlint: String = "org.jlleitschuh.gradle:ktlint-gradle:${Version.ktlint}"

    const val composeCompiler: String = "org.jetbrains.kotlin.plugin.compose:org.jetbrains.kotlin.plugin.compose.gradle.plugin:${Version.composeCompiler}"
}

dependencies {
    implementation(Plugin.kotlin)
    implementation(Plugin.androidGradle)
    implementation(Plugin.daggerHilt)
    implementation(Plugin.secretsGradle)
    implementation(Plugin.ktlint)
    implementation(Plugin.composeCompiler)

}