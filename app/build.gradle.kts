import org.jetbrains.kotlin.gradle.tasks.KotlinCompile

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

tasks.withType<KotlinCompile>().configureEach {
  kotlinOptions.freeCompilerArgs += "-opt-in=androidx.compose.material3.ExperimentalMaterial3Api"
}

dependencies {
  implementation(project(":lib"))
  implementation(project(":orb:bezier-curve"))
  implementation(project(":orb:circle-morphing"))
  implementation(project(":orb:circle-packing"))
  implementation(project(":orb:fireworks"))
  implementation(project(":orb:fourier-series"))
  implementation(project(":orb:fourier-transform"))
  implementation(project(":orb:image-dithering"))
  implementation(project(":orb:image-mosaic"))
  implementation(project(":orb:langton-ant"))
  implementation(project(":orb:life-game"))
  implementation(project(":orb:menger-sponge"))
  implementation(project(":orb:mitosis-simulation"))
  implementation(project(":orb:nearest-neighbor"))
  implementation(project(":orb:path-finding"))
  implementation(project(":orb:purple-rain"))
  implementation(project(":orb:quadtree"))
  implementation(project(":orb:raycasting"))
  implementation(project(":orb:reaction-diffusion"))
  implementation(project(":orb:sort-vision"))
  implementation(project(":orb:star-field"))
  implementation(project(":orb:steering-behaviors"))
  implementation(project(":orb:steering-evolutionary"))
  implementation(project(":orb:traveling-salesperson"))
  implementation(project(":orb:ulam-spiral"))

  implementation("org.jetbrains.kotlin:kotlin-reflect:1.6.10")

  implementation("androidx.activity:activity-compose:1.4.0")

  implementation("androidx.compose.ui:ui:1.1.1")
  implementation("androidx.compose.ui:ui-tooling:1.1.1")
  implementation("androidx.compose.foundation:foundation:1.1.1")
  implementation("androidx.compose.material:material:1.1.1")
  implementation("androidx.compose.material:material-icons-core:1.1.1")
  implementation("androidx.compose.material:material-icons-extended:1.1.1")

  implementation("com.google.android.material:material:1.5.0")
  implementation("com.google.android.material:compose-theme-adapter:1.1.5")
}
