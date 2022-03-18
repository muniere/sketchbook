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
  }
  buildTypes {
    release {
      isMinifyEnabled = false
    }
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

  implementation("androidx.fragment:fragment-ktx:1.4.1")
  implementation("org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2")
}
