plugins {
  id("com.android.application")
  id("org.jetbrains.kotlin.android")
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

tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile>().configureEach {
  kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
}

dependencies {
  implementation(project(":lib"))
  implementation(project(":orb:bezier-curve"))
  implementation(project(":orb:circle-morphing"))
  implementation(project(":orb:circle-packing"))
  implementation(project(":orb:fireworks"))
  implementation(project(":orb:image-dithering"))
  implementation(project(":orb:menger-sponge"))
  implementation(project(":orb:purple-rain"))
  implementation(project(":orb:star-field"))
  implementation(project(":orb:steering-behaviors"))

  implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

  implementation("androidx.core:core-ktx:1.7.0")
  implementation("androidx.activity:activity-ktx:1.4.0")
  implementation("androidx.fragment:fragment-ktx:1.4.1")
  implementation("androidx.activity:activity-compose:1.4.0")

  implementation("androidx.compose.ui:ui:1.1.1")
  implementation("androidx.compose.ui:ui-tooling:1.1.1")
  implementation("androidx.compose.material3:material3:1.0.0-alpha07")

  implementation("com.google.android.material:material:1.5.0")
  implementation("com.google.android.material:compose-theme-adapter:1.1.5")
}
