package extensions

import org.gradle.api.plugins.JavaPluginExtension
import org.gradle.api.JavaVersion

val ProjectExtension.Companion.JavaExtension: ProjectExtension
    get() = JavaExtension()

private class JavaExtension : ProjectExtension {

    override val name: String get() = "java"

    override fun configure(extension: Any) {
        if (extension !is JavaPluginExtension) return
        extension.apply {
            sourceCompatibility = JavaVersion.VERSION_1_8
            targetCompatibility = JavaVersion.VERSION_1_8
        }
    }
}