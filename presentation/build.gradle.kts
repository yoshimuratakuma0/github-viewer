plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.hilt)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.roborazzi)
}

android {
    namespace = "com.free.githubviewer"
    compileSdk = 35

    defaultConfig {
        minSdk = 26
        targetSdk = 35

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }

    buildTypes {
        release {
            isMinifyEnabled = false
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

    packagingOptions {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
            excludes += "/META-INF/LICENSE.md"
            excludes += "/META-INF/LICENSE-notice.md"
        }
    }
    kotlinOptions {
        jvmTarget = "21"
    }

    testOptions {
        unitTests {
            isIncludeAndroidResources = true
        }
    }
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

    androidTestImplementation(libs.ext.junit)
    androidTestImplementation(libs.mockk)
    androidTestImplementation(libs.espresso.core)
}