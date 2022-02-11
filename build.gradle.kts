import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

buildscript {
    repositories.applyDefault()
}// Top-level build file where you can add configuration options common to all sub-projects/modules.
allprojects {
    configurations.all {
        resolutionStrategy.eachDependency {
            if (requested.group == "org.jetbrains.kotlin") {
                useVersion(kotlinVersion)
            }
        }
    }
}
subprojects {
    tasks.withType<KotlinCompile>().configureEach {
        with(kotlinOptions) {
            jvmTarget = JavaVersion.VERSION_1_8.toString()
            freeCompilerArgs = freeCompilerArgs + ("-Xuse-experimental=kotlin.Experimental," +
                    "kotlinx.coroutines.ExperimentalCoroutinesApi," +
                    "kotlinx.coroutines.InternalCoroutinesApi," +
                    "kotlinx.coroutines.ObsoleteCoroutinesApi," +
                    "kotlinx.coroutines.FlowPreview," +
                    "androidx.compose.ui.ExperimentalComposeUiApi," +
                    "androidx.compose.material.ExperimentalMaterialApi," +
                    "androidx.compose.animation.ExperimentalAnimationApi," +
                    ""
            )
            freeCompilerArgs =
                    freeCompilerArgs + ("-Xopt-in=kotlin.Experimental," +
                            "androidx.compose.ui.ExperimentalComposeUiApi," +
                            "com.google.accompanist.pager.ExperimentalPagerApi,"+
                            "androidx.compose.foundation.ExperimentalFoundationApi,"+
                            "com.google.accompanist.navigation.material.ExperimentalMaterialNavigationApi"
                    )

        }
    }
}

tasks.register("clean",Delete::class) {
    delete(rootProject.buildDir)
}