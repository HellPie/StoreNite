plugins {
    id("com.android.library")
    kotlin("android")
}

android {
    compileSdk = App.sdkTargetVersion

    defaultConfig {
        minSdk = App.sdkMinVersion
    }
}
