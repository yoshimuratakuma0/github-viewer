plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.hilt)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.free.roborazzi)
}

android {
    namespace = "com.free.feature_user"

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
    implementation(libs.core.ktx)
    implementation(libs.androidx.appcompat)
    implementation(libs.material)

    testImplementation(project(":app"))
    testImplementation(project(":data"))
    testImplementation(project(":presentation"))
    testImplementation(libs.junit)
    testImplementation(libs.espresso.core)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.core)
    testImplementation(libs.mock.okhttp)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.espresso.core)
}