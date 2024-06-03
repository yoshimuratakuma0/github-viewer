// Top-level build file where you can add configuration options common to all sub-projects/modules.
buildscript {
    repositories {
        google()
        mavenCentral()
    }
    dependencies {
        classpath(libs.gradle)
        classpath(libs.kotlin.serialization)
        classpath(libs.hilt.android.gradle.plugin)
        classpath(libs.kotlin.gradle.plugin)
    }
}

plugins {
    alias(libs.plugins.android.application) apply false
    id("org.jetbrains.kotlin.android") version "1.9.24" apply false
    id("com.google.devtools.ksp") version "1.9.24-1.0.20" apply false
    id("com.google.dagger.hilt.android") version "2.48" apply false
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
