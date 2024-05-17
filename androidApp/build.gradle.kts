plugins {
    alias(libs.plugins.android)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.jetbrainsCompose)
}

android {
    namespace = "com.ragnorak.marvelcdb.android"
    compileSdk = libs.versions.android.compileSdk.get().toInt()

    sourceSets["main"].resources.srcDirs("src/commonMain/resources")

    defaultConfig {
        applicationId = "com.ragnorak.marvelcdb.android"
        minSdk = libs.versions.android.minSdk.get().toInt()
        targetSdk = libs.versions.android.targetSdk.get().toInt()
        versionCode = 1
        versionName = "1.0"
    }
    buildFeatures {
        compose = true
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
        sourceCompatibility = libs.versions.java
        targetCompatibility = libs.versions.java
    }
    composeOptions {
        kotlinCompilerExtensionVersion = libs.versions.kotlin.compiler.get()
    }
    kotlinOptions {
        jvmTarget = libs.versions.java.get()
    }
    buildToolsVersion = libs.versions.android.buildTool.get()
}

dependencies {
    implementation(project(":shared"))
    implementation(project(":components"))
    implementation(libs.androidx.material)
    implementation(libs.androidx.appcompat)
    implementation(libs.koin.core)
    implementation(libs.koin.android)
    implementation(libs.image.coil)

    // compose
    val composeBom = platform(libs.compose.bom)
    implementation (composeBom)
    androidTestImplementation (composeBom)
    implementation(libs.compose.material3)
    implementation(libs.compose.navigation)
    implementation(libs.compose.ui)
    implementation(libs.compose.animation)
    implementation(libs.compose.foundation)

    // Android Studio Preview support
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.compose.lifecycle.viewmodel)
}