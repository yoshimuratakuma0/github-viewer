import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.hilt)
    alias(libs.plugins.free.serialization)
}

try {
    val propertiesFile = rootProject.file("secret.properties")
    val properties = Properties()
    properties.load(FileInputStream(propertiesFile))
} catch (e: Exception) {
    e.printStackTrace()
}

android {
    namespace = "com.free.githubviewer"
}

dependencies {
    implementation(project(":domain"))

    implementation(libs.converter.kotlinx.serialization)
    implementation(libs.retrofit)
    implementation(libs.okhttp)
    implementation(libs.kotlinx.coroutines.core)

    testImplementation(project(":data"))

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.mock.okhttp)
    testImplementation(libs.kotlinx.coroutines.test)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}