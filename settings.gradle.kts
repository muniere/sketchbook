pluginManagement {
  val artifacts = mapOf(
    "com.android.application" to Pair("com.android.tools.build", "gradle"),
    "com.android.library" to Pair("com.android.tools.build", "gradle"),
    "org.jetbrains.kotlin.android" to Pair("org.jetbrains.kotlin", "kotlin-gradle-plugin"),
    "org.jetbrains.kotlin.plugin.serialization" to Pair("org.jetbrains.kotlin", "kotlin-serialization")
  )

  repositories {
    gradlePluginPortal()
    google()
    mavenCentral()
  }

  resolutionStrategy {
    eachPlugin {
      artifacts[requested.id.id]?.let { (group, artifact) ->
        useModule("${group}:${artifact}:${requested.version}")
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
  ":orb:fourier-series",
  ":orb:fourier-transform",
  ":orb:image-dithering",
  ":orb:image-mosaic",
  ":orb:langton-ant",
  ":orb:life-game",
  ":orb:menger-sponge",
  ":orb:nearest-neighbor",
  ":orb:path-finding",
  ":orb:purple-rain",
  ":orb:quadtree",
  ":orb:reaction-diffusion",
  ":orb:sort-vision",
  ":orb:star-field",
  ":orb:steering-behaviors",
  ":orb:traveling-salesperson",
  ":orb:ulam-spiral",
  ":lib",
)
