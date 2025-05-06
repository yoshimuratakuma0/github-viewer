plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.compose)
}

android {
    namespace = "com.free.feature_core"
}

dependencies {

    implementation(project(":design-system"))
    implementation(project(":domain"))
}