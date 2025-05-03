import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class AndroidComposeConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("org.jetbrains.kotlin.plugin.compose")
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "implementation"(libs.findLibrary("androidx.hilt.navigation.compose").get())
                "implementation"(libs.findLibrary("androidx.navigation.compose").get())
                "implementation"(libs.findLibrary("coil.compose").get())
                "implementation"(libs.findLibrary("androidx.compose.ui").get())
                "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling").get())
                "debugImplementation"(libs.findLibrary("androidx.compose.ui.tooling.preview").get())
                "debugImplementation"(libs.findLibrary("androidx.compose.ui.test.manifest").get())

                "testImplementation"(libs.findLibrary("androidx.compose.ui.test.junit4").get())
            }
        }
    }
}
