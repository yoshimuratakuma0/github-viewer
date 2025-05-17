import com.android.build.gradle.LibraryExtension
import org.gradle.api.Plugin
import org.gradle.api.Project
import org.gradle.api.artifacts.VersionCatalogsExtension
import org.gradle.kotlin.dsl.configure
import org.gradle.kotlin.dsl.dependencies
import org.gradle.kotlin.dsl.getByType

class RoborazziConventionPlugin : Plugin<Project> {
    override fun apply(target: Project) {
        with(target) {
            with(pluginManager) {
                apply("io.github.takahirom.roborazzi")
            }

            extensions.configure<LibraryExtension> {
                testOptions {
                    unitTests {
                        isIncludeAndroidResources = true
                        all {
                            it.systemProperties["robolectric.pixelCopyRenderMode"] = "hardware"
                        }
                    }
                }
            }

            val libs = extensions.getByType<VersionCatalogsExtension>().named("libs")
            dependencies {
                "testImplementation"(libs.findLibrary("roborazzi").get())
                "testImplementation"(libs.findLibrary("roborazzi.compose").get())
                "testImplementation"(libs.findLibrary("roborazzi.rule").get())
                "testImplementation"(libs.findLibrary("roborazzi.composable.preview.scanner").get())
                "testImplementation"(libs.findLibrary("compose.preview.scanner").get())
                "testImplementation"(libs.findLibrary("junit").get())
                "testImplementation"(libs.findLibrary("robolectric").get())
            }
        }
    }
}