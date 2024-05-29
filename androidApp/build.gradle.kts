plugins {
    alias(libs.plugins.android)
    alias(libs.plugins.androidApplication)
    alias(libs.plugins.mocking)
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.compose.compiler)
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
        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
    }
    buildFeatures {
        compose = true
    }

    testOptions.execution = "ANDROIDX_TEST_ORCHESTRATOR"

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
    implementation(libs.koin.compose)
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
    implementation(libs.kotlinx.serialization.json)

    //Room
    implementation(libs.room.runtime.android)

    // Android Studio Preview support
    implementation(libs.compose.ui.tooling.preview)
    debugImplementation(libs.compose.ui.tooling)

    // Optional - Integration with activities
    implementation(libs.androidx.activity.compose)
    // Optional - Integration with ViewModels
    implementation(libs.compose.lifecycle.viewmodel)

    testImplementation(libs.kotlinx.coroutines.test)
    testImplementation(libs.test.turbine)

    androidTestImplementation(libs.compose.testing.junit)
    androidTestImplementation(libs.koin.core)
    androidTestImplementation(libs.test.turbine)
    debugImplementation(libs.compose.testing.manifest)
    androidTestImplementation (libs.androidx.runner)
    androidTestUtil (libs.androidx.orchestrator)
}