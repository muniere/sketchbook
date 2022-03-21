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
  api(fileTree("libs/processing-core-4.2.1.jar"))

  api("androidx.core:core-ktx:1.7.0")
  api("androidx.activity:activity-ktx:1.4.0")
  api("androidx.fragment:fragment-ktx:1.4.1")
}
