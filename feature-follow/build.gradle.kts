plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.hilt)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.free.roborazzi)
}

android {
    namespace = "com.free.feature_follow"

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
    implementation(project(":domain"))
    implementation(project(":design-system"))
    implementation(project(":feature-core"))
    implementation(project(":feature-user"))

    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)
    testImplementation(libs.junit)
    testImplementation(libs.robolectric)
    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}