plugins {
  id("com.android.application").version("7.1.2").apply(false)
  id("com.android.library").version("7.1.2").apply(false)
  id("org.jetbrains.kotlin.android").version("1.6.10").apply(false)
  id("org.jetbrains.kotlin.plugin.serialization").version("1.6.10").apply(false)
}

tasks.register("clean", type = Delete::class) {
  delete(rootProject.buildDir)
}

// https://github.com/gradle/gradle/issues/4823#issuecomment-715615422
subprojects {
  if (gradle.startParameter.isConfigureOnDemand && buildscript.sourceFile?.extension?.toLowerCase() == "kts" && parent != rootProject) {
    generateSequence(parent) { it.parent }.filter { it != rootProject }.forEach { evaluationDependsOn(it.path) }
  }
}
