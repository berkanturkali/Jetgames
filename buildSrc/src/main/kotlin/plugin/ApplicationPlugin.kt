package plugin

import androidTestImplementation
import daggerHilt
import extensions.AndroidApp
import extensions.ProjectExtension
import extensions.libs
import implementation
import kotlinAndroid
import kotlinKapt
import org.gradle.kotlin.dsl.dependencies
import testImplementation
import composeCompiler

class ApplicationPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(APP_PLUGIN_ID)
            kotlinAndroid
            kotlinKapt
            daggerHilt
            composeCompiler
        }
    override val libraryConfig: LibraryConfig
        get() = {
            dependencies {
                implementation(
                    libs.findLibrary("core.ktx").get().get(),
                    libs.findLibrary("compose-activity").get().get(),
                    libs.findLibrary("material").get().get(),
                    libs.findLibrary("timber").get().get(),
                    libs.findLibrary("compose-runtime").get().get(),
                )
                testImplementation(libs.findLibrary("junit").get().get())
                androidTestImplementation(
                    libs.findLibrary("junit.ext").get().get(),
                    libs.findLibrary("espresso").get().get(),
                    libs.findLibrary("compose.junit").get().get()
                )
            }
        }

    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidApp)

    private companion object {
        const val APP_PLUGIN_ID: String = "com.android.application"
    }

}