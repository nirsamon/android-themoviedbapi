import java.nio.charset.Charset
import java.util.Properties

plugins {
    id("com.android.application")
    id("dagger.hilt.android.plugin")
    id("kotlinx-serialization")
    kotlin("android")
    kotlin("kapt")
}

android {
    namespace = "com.nino.codilitytask3tmdb"
    buildFeatures {
        compose = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.composeCompiler.get()
    }

    buildTypes {
        getByName("debug") {
            isDefault = true
            applicationIdSuffix = ".debug"
            versionNameSuffix = "-DEBUG"
        }
        getByName("release") {
            isShrinkResources = true
            isMinifyEnabled = true
            isDebuggable = false
            proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
        }
    }

    packagingOptions.apply {
        resources.excludes.addAll(
            listOf(
                "META-INF/licenses/**",
                "META-INF/AL2.0",
                "META-INF/LGPL2.1",
            ),
        )
    }
    compileSdk = 33
}

kapt {
    correctErrorTypes = true
}

dependencies {
    implementation(libs.bundles.androidX)
    implementation(libs.bundles.compose)
    implementation(libs.bundles.io)
    implementation(libs.coil)
    implementation(libs.hilt)
    kapt(libs.hiltCompiler)
    debugImplementation(libs.compose.testManifest)
    testImplementation(libs.bundles.test)
    androidTestImplementation(libs.bundles.androidTest)
}
