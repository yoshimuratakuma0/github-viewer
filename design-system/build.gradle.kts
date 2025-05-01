plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.free.roborazzi)
}

android {
    namespace = "com.free.design_system"
}

dependencies {

    implementation(libs.core.ktx)
    testImplementation(libs.junit)
}