plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.free.roborazzi)
}

android {
    namespace = "com.free.design_system"

    flavorDimensions += "mode"
    productFlavors {
        create("fake") {
            dimension = "mode"
        }
        create("prod") {
            dimension = "mode"
        }
    }
}

dependencies {

    implementation(libs.androidx.compose.material3)
}

roborazzi {
    generateComposePreviewRobolectricTests {
        enable = true
        includePrivatePreviews = true
        packages = listOf(
            "com.free.design_system"
        )
    }
}