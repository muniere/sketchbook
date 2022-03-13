plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
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

dependencies {
  api(project(":lib"))

  implementation("org.jetbrains.kotlinx:kotlinx-coroutines-android:1.6.0")
  implementation("androidx.activity:activity-ktx:1.4.0")
  implementation("androidx.fragment:fragment-ktx:1.4.1")
  implementation("androidx.lifecycle:lifecycle-livedata-ktx:2.4.1")

  implementation("androidx.compose.ui:ui:1.1.1")
  implementation("androidx.compose.ui:ui-tooling:1.1.1")
  implementation("androidx.compose.runtime:runtime-livedata:1.1.1")
  implementation("androidx.compose.material3:material3:1.0.0-alpha07")
}
