plugins {
    id("com.android.application")
    kotlin("android")
}

android {
    namespace = "com.ragnorak.marvelcdb.android"
    compileSdk = 34
    defaultConfig {
        applicationId = "com.ragnorak.marvelcdb.android"
        minSdk = 28
        targetSdk = 34
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
    }
    composeOptions {
        kotlinCompilerExtensionVersion = "1.5.14"
    }
    packaging {
        resources {
            excludes += "/META-INF/{AL2.0,LGPL2.1}"
        }
    }
    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    kotlinOptions {
        jvmTarget = "17"
    }
    buildToolsVersion = "34.0.0"
}

val koinVersion = "3.2.0"

dependencies {
    implementation(project(":shared"))
    implementation("com.google.android.material:material:1.11.0")
    implementation("androidx.appcompat:appcompat:1.6.1")
    implementation("androidx.constraintlayout:constraintlayout:2.1.4")
    implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.8.1")
    implementation("io.insert-koin:koin-core:$koinVersion")
    implementation( "io.insert-koin:koin-android:$koinVersion")

    // compose
    val composeBom = platform("androidx.compose:compose-bom:2024.05.00")
    implementation (composeBom)
    androidTestImplementation (composeBom)

    implementation("androidx.compose.material3:material3")
    implementation("androidx.compose.ui:ui")

    // Android Studio Preview support
    implementation("androidx.compose.ui:ui-tooling-preview")
    debugImplementation("androidx.compose.ui:ui-tooling")

    // Optional - Integration with activities
    implementation("androidx.activity:activity-compose:1.9.0")
    // Optional - Integration with ViewModels
    implementation("androidx.lifecycle:lifecycle-viewmodel-compose:2.8.0")
}