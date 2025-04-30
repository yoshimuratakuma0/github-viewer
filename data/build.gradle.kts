import java.io.FileInputStream
import java.util.Properties

plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.hilt)
    alias(libs.plugins.free.serialization)
}

val propertiesFile = rootProject.file("secret.properties")
val properties = Properties()
try {
    properties.load(FileInputStream(propertiesFile))
} catch (e: Exception) {
    e.printStackTrace()
}

android {
    namespace = "com.free.githubviewer"
    compileSdk = 35

    buildFeatures {
        buildConfig = true
    }

    defaultConfig {
        minSdk = 26
        targetSdk = 35

        buildConfigField(
            "String",
            "GITHUB_TOKEN",
            (properties["GITHUB_TOKEN"] as? String) ?: "\"\""
        )
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_21
        targetCompatibility = JavaVersion.VERSION_21
    }
    kotlinOptions {
        jvmTarget = "21"
    }
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