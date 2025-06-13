package plugin

import extensions.JavaExtension
import extensions.KotlinExtension
import extensions.ProjectExtension
import extensions.libs
import kotlinKapt
import implementation
import kapt
import org.gradle.api.artifacts.VersionCatalog
import org.gradle.api.artifacts.dsl.DependencyHandler
import org.gradle.kotlin.dsl.dependencies


class KotlinLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(KOTLIN_PLUGIN_ID)
            kotlinKapt
        }
    override val libraryConfig: LibraryConfig
        get() = {
            dependencies {
                implementation(
                    libs.findLibrary("hilt.core").get().get()
                )
                kapt(libs.findLibrary("androidx.hilt.compiler").get().get())
            }
        }

    override val extensions: Array<ProjectExtension>
        get() = arrayOf(
            ProjectExtension.KotlinExtension,
            ProjectExtension.JavaExtension
        )

    private companion object {
        const val KOTLIN_PLUGIN_ID: String = "kotlin"
    }
}