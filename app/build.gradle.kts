plugins {
    id("com.android.application")
    kotlin("android")
    kotlin("kapt")
    kotlin("plugin.serialization")

    // AndroidX
    //id "androidx.navigation.safeargs.kotlin"
    //id "androidx.benchmark"

    id("dagger.hilt.android.plugin")
}

kapt {
    correctErrorTypes = true
}

hilt {
    enableAggregatingTask = true
}

android {
    compileSdk = App.sdkTargetVersion

    defaultConfig {
        applicationId = App.id

        targetSdk = App.sdkTargetVersion
        minSdk = App.sdkMinVersion

        versionCode = App.appVersion
        versionName = App.appVersionString

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        testInstrumentationRunner = "androidx.benchmark.junit4.AndroidBenchmarkRunner"
    }

    signingConfigs {
        named("debug") {
            storeFile = rootProject.file("signing/debug.jks")
            storePassword = "debugcert"
            keyPassword = "debugcert"
            keyAlias = "debugcert"
        }

        maybeCreate("release")
        named("release") {
            storeFile = rootProject.file("signing/release.jks")
        }
    }

    buildTypes {
        debug {
            signingConfig = signingConfigs.findByName("debug")

            versionNameSuffix = "-DEBUG"
            applicationIdSuffix = ".debug"
        }

        release {
            signingConfig = signingConfigs.findByName("release")

            isMinifyEnabled = true
            isShrinkResources = true

            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }

    bundle {
        language.enableSplit = false
    }

    lint {
        abortOnError = false
    }

    buildFeatures {
        compose = true
        dataBinding = true
    }

    composeOptions {
        kotlinCompilerExtensionVersion = Libs.Compose.version
    }

    kotlinOptions {
        jvmTarget = "1.8"
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }

    packagingOptions {
        resources.excludes += "DebugProbesKt.bin"
    }
}

dependencies {
    // Modules
    implementation(project(":modules:base"))
    implementation(project(":modules:i18n"))

    // Kotlin
    implementation(Libs.Kotlin.Coroutines.android)
    implementation(Libs.Kotlin.Serialization.json)

    // AndroidX
    kapt(Libs.AndroidX.Lifecycle.compiler)
    implementation(Libs.AndroidX.AppCompat.core)
    implementation(Libs.AndroidX.AppCompat.resources)
    implementation(Libs.AndroidX.Layouts.activity)
    implementation(Libs.AndroidX.Layouts.material3)

    // Dagger
    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.androidCompiler)
    implementation(Libs.Dagger.android)

    kapt(Libs.Dagger.Hilt.compiler)
    implementation(Libs.Dagger.Hilt.android)
    testImplementation(Libs.Dagger.Hilt.androidTest)

    kapt(Libs.Dagger.AndroidX.compiler)
    implementation(Libs.Dagger.AndroidX.core)
    implementation(Libs.Dagger.AndroidX.compose)
    implementation(Libs.Dagger.AndroidX.fragment)
    implementation(Libs.Dagger.AndroidX.navigation)
    implementation(Libs.Dagger.AndroidX.workManager)

    // Instrumentation
    debugImplementation(Libs.Utils.leakCanary)
}
