plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.compose)
}

android {
    namespace = "com.free.feature_core"
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

    implementation(project(":design-system"))
    implementation(project(":domain"))
}