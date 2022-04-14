buildscript {
    repositories {
        gradlePluginPortal()
        google()
        mavenCentral()
    }

    dependencies {
        classpath(Libs.Gradle.android)
        classpath(Libs.Gradle.googleServices)
        classpath(Libs.Kotlin.gradle)
        classpath(Libs.Kotlin.Serialization.gradle)
        classpath(Libs.AndroidX.Navigation.gradle)
        classpath(Libs.Dagger.Hilt.gradle)
    }
}

plugins {
    id("com.diffplug.spotless") version "6.1.0"
    id("com.github.ben-manes.versions") version "0.42.0"
    id("com.kncept.junit.reporter") version "2.1.0"
}

allprojects {
    repositories {
        google()
        mavenCentral()
    }
}

subprojects {
    configurations.configureEach {
        exclude(group = "androidx.appcompat")
        exclude(group = "com.google.android.material", module = "material")
    }

    apply(plugin = "com.diffplug.spotless")
    spotless {
        kotlin {
            licenseHeaderFile(project.rootProject.file("spotless/copyright.kt"))
            ktlint("0.41.0").userData(mapOf(
                "android"        to "true",
                "max_line_length" to "120"
            ))
        }

        format("misc") {
            target(listOf("**/*.gradle", "**/*.md", "**/.gitignore"))
            trimTrailingWhitespace()
            indentWithTabs()
        }
    }
}

tasks.register("clean", Delete::class) {
    delete(rootProject.buildDir)
}
