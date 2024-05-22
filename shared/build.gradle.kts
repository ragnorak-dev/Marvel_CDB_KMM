plugins {
    alias(libs.plugins.kotlinSerialization)
    alias(libs.plugins.kotlinMultiplatform)
    alias(libs.plugins.androidLibrary)
    alias(libs.plugins.jetbrainsCompose)
    alias(libs.plugins.mocking)
    alias(libs.plugins.ksp)
    alias(libs.plugins.room)
}

@OptIn(org.jetbrains.kotlin.gradle.ExperimentalKotlinGradlePluginApi::class)
kotlin {
    applyDefaultHierarchyTemplate()

    androidTarget {
        compilations.all {
            kotlinOptions {
                jvmTarget = libs.versions.java.get()
            }
        }
    }

    listOf(
        iosX64(),
        iosArm64(),
        iosSimulatorArm64()
    ).forEach {
        it.binaries.framework {
            baseName = "shared"
        }
    }

    sourceSets {

        commonMain.dependencies {
            implementation(libs.kotlinx.coroutines.core)
            implementation(libs.ktor.client.core)
            implementation(libs.ktor.client.contentNegotiation)
            implementation(libs.ktor.serialization.json)
            implementation(libs.ktor.serialization)
            implementation(libs.room.runtime)
            implementation(libs.sqlite.bundled)
            api(project.dependencies.platform(libs.koin.bom))
            api(libs.koin.core)
            api(libs.koin.test)

        }

        commonTest.dependencies {
            implementation(kotlin("test"))
        }

        androidMain.dependencies {
            implementation(libs.ktor.client.okhttp)
            implementation(libs.compose.runtime)
        }

        iosMain.dependencies {
            implementation(libs.ktor.client.darwin)
        }
    }
}

android {
    namespace = "com.ragnorak.marvelcdb"
    compileSdk = libs.versions.android.compileSdk.get().toInt()
    defaultConfig {
        minSdk = libs.versions.android.minSdk.get().toInt()
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_17
        targetCompatibility = JavaVersion.VERSION_17
    }
    buildToolsVersion = libs.versions.android.buildTool.get()
}

room {
    schemaDirectory("$projectDir/schemas")
}

dependencies {
    testImplementation(libs.ktor.client.test)
    ksp(libs.room.compiler)
}

