plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.hilt)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.free.githubviewer"
}

dependencies {
    implementation(project(":domain"))
    implementation(libs.core.ktx)

    testImplementation(libs.junit)
    testImplementation(libs.mockk)
    testImplementation(libs.kotlinx.coroutines.test)

    testImplementation(libs.espresso.core)
    testImplementation(libs.robolectric)
    testImplementation(libs.androidx.core)
    testImplementation(project(":data"))
    testImplementation(project(":app"))

    testImplementation(libs.roborazzi)
    testImplementation(libs.roborazzi.compose)
    testImplementation(libs.roborazzi.rule)
    testImplementation(libs.mock.okhttp)

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.mockk)
    androidTestImplementation(libs.espresso.core)
}