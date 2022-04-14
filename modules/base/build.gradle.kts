plugins {
    id("com.android.library")
    kotlin("android")
    kotlin("kapt")
}

android {
    compileSdk = App.sdkTargetVersion

    defaultConfig {
        minSdk = App.sdkMinVersion
    }

    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
}

dependencies {
    api(project(":modules:i18n"))

    api(Libs.Kotlin.stdLib)
    api(Libs.Kotlin.Serialization.json)
    api(Libs.Kotlin.Coroutines.core)

    kapt(Libs.Dagger.compiler)
    kapt(Libs.Dagger.androidCompiler)
    kapt(Libs.Dagger.Hilt.compiler)
    api(Libs.Dagger.android)
    api(Libs.Dagger.Hilt.android)

    api(Libs.Utils.timber)
}
