package plugin

import androidModule
import androidTestImplementation
import extensions.AndroidLib
import extensions.ProjectExtension
import implementation
import kotlinAndroid
import kotlinKapt
import testImplementation

class AndroidLibraryPlugin : BasePlugin() {
    override val pluginConfig: PluginConfig
        get() = {
            androidModule
            kotlinAndroid
            kotlinKapt
        }
    override val libraryConfig: LibraryConfig
        get() = {
            implementation("androidx.core:core-ktx:1.7.0",
                "androidx.appcompat:appcompat:1.4.1",
                "com.google.android.material:material:1.5.0")
            testImplementation("junit:junit:4.13.2")
            androidTestImplementation("androidx.test.ext:junit:1.1.3",
                "androidx.test.espresso:espresso-core:3.4.0")
        }
    override val extensions: Array<ProjectExtension>
        get() = arrayOf(ProjectExtension.AndroidLib)
}