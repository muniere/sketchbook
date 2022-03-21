import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.plugin.serialization")
}

android {
  compileSdk = 31

  defaultConfig {
    minSdk = 30
    targetSdk = 31
  }

  buildFeatures {
    buildConfig = false
    compose = true
  }
  buildTypes {
    release {
      isMinifyEnabled = false
    }
  }
  composeOptions {
    kotlinCompilerExtensionVersion = "1.1.1"
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_8
    targetCompatibility = JavaVersion.VERSION_1_8
  }
  kotlinOptions {
    jvmTarget = JavaVersion.VERSION_1_8.toString()
  }
}

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material.ExperimentalMaterialApi"
  kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.foundation.ExperimentalFoundationApi"
}

dependencies {
  api(project(":lib"))

  implementation("androidx.activity:activity-compose:1.4.0")

  implementation("androidx.compose.ui:ui:1.1.1")
  implementation("androidx.compose.ui:ui-tooling:1.1.1")
  implementation("androidx.compose.material:material:1.1.1")
  implementation("androidx.compose.material3:material3:1.0.0-alpha07")

  implementation("com.google.android.material:material:1.5.0")
  implementation("com.google.android.material:compose-theme-adapter:1.1.5")

  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}
