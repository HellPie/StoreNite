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
    // Kotlin
    implementation(Libs.Kotlin.stdLib)
    implementation(Libs.Kotlin.Coroutines.android)
    implementation(Libs.Kotlin.Serialization.json)
    implementation(Libs.Kotlin.KotlinX.dateTime)

    // AndroidX
    kapt(Libs.AndroidX.Lifecycle.compiler)
    implementation(Libs.AndroidX.AppCompat.core)
    implementation(Libs.AndroidX.AppCompat.resources)
    implementation(Libs.AndroidX.Layouts.activity)
    implementation(Libs.AndroidX.Layouts.material3)

    // Compose
    kapt(Libs.Compose.compiler)
    implementation(Libs.Compose.runtime)
    implementation(Libs.Compose.liveData)
    implementation(Libs.Compose.ui)
    implementation(Libs.Compose.foundation)
    implementation(Libs.Compose.animation)
    implementation(Libs.Compose.material3)
    implementation(Libs.Compose.preview)
    debugImplementation(Libs.Compose.tooling)

    implementation(Libs.Compose.Accompanist.insetsUi)
    implementation(Libs.Compose.Accompanist.systemUiController)
    implementation(Libs.Compose.Accompanist.appCompatTheme)
    implementation(Libs.Compose.Accompanist.pager)
    implementation(Libs.Compose.Accompanist.pagerIndicators)
    implementation(Libs.Compose.Accompanist.swipeRefresh)
    implementation(Libs.Compose.Accompanist.placeholder)
    implementation(Libs.Compose.Accompanist.placeholderMaterial)
    implementation(Libs.Compose.Accompanist.drawablePainter)
    implementation(Libs.Compose.Accompanist.flowLayout)
    implementation(Libs.Compose.Accompanist.permissions)
    implementation(Libs.Compose.Accompanist.navigationAnimation)
    implementation(Libs.Compose.Accompanist.navigationMaterial)
    implementation(Libs.Compose.Accompanist.webView)

    implementation(Libs.Compose.ThirdParty.reorderable)

    // Data
    implementation(Libs.AndroidX.DataStore.Preferences.core)

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
    //implementation(Libs.Dagger.AndroidX.workManager)

    // Ktor
    implementation(Libs.Network.Ktor.core)
    implementation(Libs.Network.Ktor.okHttp)
    implementation(Libs.Network.Ktor.Serialization.json)
    implementation(Libs.Network.Ktor.Extensions.clientResources)
    implementation(Libs.Network.Ktor.Extensions.contentNegotiation)
    implementation(Libs.Network.Ktor.Extensions.logging)
    testImplementation(Libs.Network.Ktor.test)

    // Coil
    implementation(Libs.Network.Coil.core)
    implementation(Libs.Network.Coil.compose)
    implementation(Libs.Network.Coil.video)

    // Instrumentation
    implementation(Libs.Utils.timber)
    debugImplementation(Libs.Utils.leakCanary)

    // Utils
    implementation(Libs.AndroidX.Utils.annotation)
}
