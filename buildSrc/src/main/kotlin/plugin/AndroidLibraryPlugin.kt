package plugin

import Library
import androidModule
import androidTestImplementation
import extensions.AndroidLib
import extensions.ProjectExtension
import implementation
import kotlinAndroid
import kotlinKapt
import testImplementation
import debugImplementation

class AndroidLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            androidModule
            kotlinAndroid
            kotlinKapt
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementation(
                Library.coreKtx,
                Library.appcompat,
                Library.material,
                Library.timber)
            testImplementation(Library.junit)
            androidTestImplementation(
                Library.junitExt,
                Library.espresso)

            debugImplementation(Library.composeUiTooling)
        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidLib)
}