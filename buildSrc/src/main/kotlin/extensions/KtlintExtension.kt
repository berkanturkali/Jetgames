package extensions

import org.jlleitschuh.gradle.ktlint.KtlintExtension

val ProjectExtension.Companion.Ktlint: ProjectExtension
    get() = AppKtlintExtension()

private class AppKtlintExtension : ProjectExtension {

    override val name: String get() = "ktlint"

    override fun configure(extension: Any) {
        if (extension !is KtlintExtension) return
        extension.apply {
            version.set("0.43.2")
            outputToConsole.set(true)
            disabledRules.add("import-ordering")
            disabledRules.add("no-wildcard-imports")
            filter {
                exclude("**/generated/**")
                include("**/kotlin/**")
            }
        }
    }
}