import org.jetbrains.kotlin.gradle.tasks.KotlinCompile


plugins {
    ktlint
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}
subprojects project@{
    applyKtlint
    tasks.withType<KotlinCompile>().configureEach {
        with(kotlinOptions) {
            jvmTarget = JavaVersion.VERSION_11.toString()
            freeCompilerArgs =
                freeCompilerArgs + (
                "-Xopt-in=kotlin.Experimental," +
                    "androidx.compose.ui.ExperimentalComposeUiApi," +
                    "com.google.accompanist.pager.ExperimentalPagerApi," +
                    "androidx.compose.foundation.ExperimentalFoundationApi," +
                    "com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.ObsoleteCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview," +
                    "androidx.compose.ui.ExperimentalComposeUiApi," +
                    "androidx.compose.material.ExperimentalMaterialApi," +
                    "androidx.compose.animation.ExperimentalAnimationApi,"
                )
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}