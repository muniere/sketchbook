pluginManagement {
  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }

  resolutionStrategy {
    eachPlugin {
      when (requested.id.namespace) {
        "com.android" -> useModule("com.android.tools.build:gradle:${requested.version}")
        "com.google.gms" -> useModule("com.google.gms:${requested.id.name}:${requested.version}")
      }
    }
  }
}

dependencyResolutionManagement {
  repositoriesMode.set(RepositoriesMode.FAIL_ON_PROJECT_REPOS)
  repositories {
    google()
    mavenCentral()
  }
}

rootProject.name = "sketchbook"

include(
  ":app",
  ":orb:bezier-curve",
  ":orb:circle-morphing",
  ":orb:circle-packing",
  ":orb:fireworks",
  ":orb:menger-sponge",
  ":orb:purple-rain",
  ":orb:star-field",
  ":lib",
)
