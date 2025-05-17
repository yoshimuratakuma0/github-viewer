plugins {
    alias(libs.plugins.free.android.library)
    alias(libs.plugins.free.compose)
    alias(libs.plugins.free.roborazzi)
}

android {
    namespace = "com.design_system"
}

dependencies {

    implementation(libs.androidx.compose.material3)
}

roborazzi {
    generateComposePreviewRobolectricTests {
        enable = true

        packages = listOf(
            "com.design_system"
        )

//        // ComposePreviewTesterの実装クラスの名前(これから実装する)
//        testerQualifiedClassName = "com.google.samples.apps.nowinandroid.MyComposePreviewTester"

        // プレビュー関数を集めるパッケージ名。ここではfeature.interestsとfeature.foryouだけにしている
//        packages = listOf(
//            "com.google.samples.apps.nowinandroid.feature.interests",
//            "com.google.samples.apps.nowinandroid.feature.foryou"
//        )
    }
}