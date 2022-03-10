package plugin

import Library
import androidTestImplementation
import daggerHilt
import extensions.AndroidApp
import extensions.ProjectExtension
import implementation
import kotlinAndroid
import kotlinKapt
import testImplementation

class ApplicationPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            apply(APP_PLUGIN_ID)
            kotlinAndroid
            kotlinKapt
            daggerHilt
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementation(Library.coreKtx,
                Library.activity,
                Library.daggerHiltAndroid,
                Library.navComponent,
                Library.navigation)
            testImplementation(Library.junit)
            androidTestImplementation(Library.junitExt, Library.espresso, Library.composeJunit)

        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidApp)

    private companion object {
        const val APP_PLUGIN_ID: String = "com.android.application"
    }

}