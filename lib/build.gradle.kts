plugins {
  id("com.android.library")
  id("org.jetbrains.kotlin.android")
}

android {
  compileSdk = 31

  defaultConfig {
    minSdk = 30
    targetSdk = 31

    consumerProguardFiles("consumer-rules.pro")
  }

  buildFeatures {
    viewBinding = true
  }
  buildTypes {
    release {
      isMinifyEnabled = false
      proguardFiles(getDefaultProguardFile("proguard-android-optimize.txt"), "proguard-rules.pro")
    }
  }
  compileOptions {
    sourceCompatibility = JavaVersion.VERSION_1_6
    targetCompatibility = JavaVersion.VERSION_1_6
  }
  kotlinOptions {
    jvmTarget = "1.6"
  }
}

dependencies {
  api(fileTree("libs/processing-core-4.2.1.jar"))
}
