package plugin

import androidModule
import androidTestImplementation
import composeCompiler
import debugImplementation
import extensions.AndroidLib
import extensions.ProjectExtension
import extensions.libs
import implementation
import kotlinAndroid
import kotlinKapt
import org.gradle.kotlin.dsl.dependencies
import testImplementation


class AndroidLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            androidModule
            kotlinAndroid
            kotlinKapt
            composeCompiler
        }

    override val libraryConfig: LibraryConfig
        get() = {
            dependencies {
                implementation(
                    libs.findLibrary("core.ktx").get().get(),
                    libs.findLibrary("appcompat").get().get(),
                    libs.findLibrary("material").get().get(),
                    libs.findLibrary("timber").get().get()
                )
                testImplementation(libs.findLibrary("junit").get().get())
                androidTestImplementation(
                    libs.findLibrary("junit.ext").get().get(),
                    libs.findLibrary("espresso").get().get()
                )
                debugImplementation(libs.findLibrary("compose.ui.tooling").get().get())
            }
        }

    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidLib)
}