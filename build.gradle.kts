// Top-level build file where you can add configuration options common to all sub-projects/modules.
plugins {
    alias(libs.plugins.android.application) apply false
    alias(libs.plugins.jetbrains.kotlin.android) apply false
}

buildscript {
    repositories {
        google()
    }
    dependencies {
        // Navigation dependency
//        val nav_version = "2.7.7"
        classpath(libs.androidx.navigation.safe.args.gradle.plugin)
    }
}


//plugins {
//    id("com.android.application") version "8.2.1" apply false
//    id("org.jetbrains.kotlin.android") version "1.9.22" apply false
//}