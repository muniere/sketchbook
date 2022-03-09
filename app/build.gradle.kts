plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
  id("org.jetbrains.kotlin.kapt")
}

android {
  compileSdk = 31

  defaultConfig {
    applicationId = "net.muniere.sketchbook"
    minSdk = 30
    targetSdk = 31
    versionCode = 1
    versionName = "1.0.0"
  }

  buildFeatures {
    viewBinding = true
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
  implementation(project(":lib"))
  implementation(project(":orb:star-field"))
  implementation(project(":orb:menger-sponge"))
  implementation(project(":orb:purple-rain"))
  implementation(project(":orb:fireworks"))
  implementation(project(":orb:circle-morphing"))
  implementation(project(":orb:bezier-curve"))

  implementation(kotlin("reflect"))
  implementation("androidx.core:core-ktx:1.7.0")
  implementation("androidx.fragment:fragment-ktx:1.4.1")
  implementation("com.google.android.material:material:1.5.0")

  implementation("com.airbnb.android:epoxy:4.6.3")
  kapt("com.airbnb.android:epoxy-processor:4.6.3")
}
