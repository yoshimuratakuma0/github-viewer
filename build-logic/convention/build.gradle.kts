import org.jetbrains.kotlin.gradle.dsl.JvmTarget

plugins {
    `kotlin-dsl`
}

group = "com.free.githubviewer.buildlogic"

java {
    sourceCompatibility = JavaVersion.VERSION_21
    targetCompatibility = JavaVersion.VERSION_21
}

kotlin {
    compilerOptions {
        jvmTarget = JvmTarget.JVM_21
    }
}

tasks {
    validatePlugins {
        enableStricterValidation = true
        failOnWarning = true
    }
}

gradlePlugin {
    plugins {
        register("androidApplication") {
            id = "free.android.application"
            implementationClass = "AndroidApplicationConventionPlugin"
        }
        register("androidLibrary") {
            id = "free.android.library"
            implementationClass = "AndroidLibraryConventionPlugin"
        }
        register("androidHilt") {
            id = "free.hilt"
            implementationClass = "AndroidHiltConventionPlugin"
        }
        register("serialization") {
            id = "free.serialization"
            implementationClass = "AndroidSerializationConventionPlugin"
        }
        register("androidCompose") {
            id = "free.compose"
            implementationClass = "AndroidComposeConventionPlugin"
        }
    }
}