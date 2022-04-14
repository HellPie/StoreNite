

object Libs {
    object Gradle {
        const val android = "com.android.tools.build:gradle:7.1.3"
        const val googleServices = "com.google.gms:google-services:4.3.10"
        const val dexCount = "com.getkeepsafe.dexcount:dexcount-gradle-plugin:3.1.0"
    }

    object Kotlin {
        private const val version = "1.6.10"
        const val stdLib = "org.jetbrains.kotlin:kotlin-stdlib-jdk8:$version"
        const val gradle = "org.jetbrains.kotlin:kotlin-gradle-plugin:$version"

        object Serialization {
            const val gradle = "org.jetbrains.kotlin:kotlin-serialization:$version"
            const val json = "org.jetbrains.kotlinx:kotlinx-serialization-json:1.3.2"

            object ThirdParty {
                const val preferences = "net.edwardday.serialization:kprefs:0.11.0"
                const val bundle = "dev.ahmedmourad.bundlizer:bundlizer-core:0.7.0"
            }
        }

        object Coroutines {
            private const val version = "1.6.1"
            const val core = "org.jetbrains.kotlinx:kotlinx-coroutines-core:$version"
            const val android = "org.jetbrains.kotlinx:kotlinx-coroutines-android:$version"
            const val test = "org.jetbrains.kotlinx:kotlinx-coroutines-test:$version"
        }

        object KotlinX {
            const val dateTime = "org.jetbrains.kotlinx:kotlinx-datetime:0.3.2"
        }
    }

    object AndroidX {
        object Core {
            private const val animationVersion = "1.0.0-alpha02"
            const val core = "androidx.core:core-ktx:1.9.0-alpha02"
            const val performance = "androidx.core:core-performance:1.0.0-alpha02"
            const val splashScreen = "androidx.core:core-splashscreen:1.0.0-beta02"
            const val remoteViews = "androidx.core:core-remoteviews:1.0.0-alpha03"
            const val shortcuts = "androidx.core:core-google-shortcuts:1.1.0-alpha01"
            const val animation = "androidx.core:core-animation:$animationVersion"
            const val animationTest = "androidx.core:core-animation-testing:$animationVersion"
        }

        object AppCompat {
            private const val version = "1.5.0-alpha01"
            const val core = "androidx.appcompat:appcompat:$version"
            const val resources = "androidx.appcompat:appcompat-resources:$version"
        }

        object Layouts {
            const val activity = "androidx.activity:activity-ktx:1.5.0-alpha05"
            const val coordinatorLayout = "androidx.coordinatorlayout:coordinatorlayout:1.2.0"
            const val slidingPaneLayout = "androidx.slidingpanelayout:slidingpanelayout:1.2.0"
            const val swipeRefreshLayout = "androidx.swiperefreshlayout:swiperefreshlayout:1.2.0-alpha01"
            const val viewPager2 = "androidx.viewpager2:viewpager2:1.1.0-beta01"
            const val transition = "androidx.transition:transition-ktx:1.4.1"
            const val material3 = "com.google.android.material:material:1.6.0-beta01"

            object Fragment {
                private const val version = "1.4.1"
                const val core = "androidx.fragment:fragment-ktx:$version"
                const val debug = "androidx.fragment:fragment-testing:$version"
            }

            object ConstraintLayout {
                const val core = "androidx.constraintlayout:constraintlayout:2.1.3"
                const val compose = "androidx.constraintlayout:constraintlayout-compose:1.0.0"
            }

            object RecyclerView {
                const val core = "androidx.recyclerview:recyclerview:1.3.0-alpha02"
                const val selection = "androidx.recyclerview:recyclerview-selection:1.2.0-alpha01"
            }

            object Slice {
                private const val version = "1.1.0-alpha02"
                const val core = "androidx.slice:slice-core:$version"
                const val builders = "androidx.slice:slice-builders:$version"
                const val view = "androidx.slice:slice-view:$version"
            }

            object Window {
                private const val version = "1.0.0"
                const val core = "androidx.window:window:$version"
                const val rxJava3 = "androidx.window:window-rxjava3:$version"
                const val test = "androidx.window:window-testing:$version"
            }
        }

        object Navigation {
            private const val version = "2.5.0-alpha04"
            const val gradle = "androidx.navigation:navigation-safe-args-gradle-plugin:$version"
            const val runtime = "androidx.navigation:navigation-runtime-ktx:$version"
            const val fragment = "androidx.navigation:navigation-fragment-ktx:$version"
            const val ui = "androidx.navigation:navigation-ui-ktx:$version"
            const val compose = "androidx.navigation:navigation-compose:$version"
            const val dynamicFeatures = "androidx.navigation:navigation-dynamic-features-fragment:$version"
            const val test = "androidx.navigation:navigation-testing:$version"
        }

        object Lifecycle {
            private const val version = "2.5.0-alpha06"
            const val compiler = "androidx.lifecycle:lifecycle-compiler:$version"
            const val runtime = "androidx.lifecycle:lifecycle-runtime-ktx:$version"
            const val liveData = "androidx.lifecycle:lifecycle-livedata-ktx:$version"
            const val viewModel = "androidx.lifecycle:lifecycle-viewmodel-ktx:$version"
            const val savedState = "androidx.lifecycle:lifecycle-viewmodel-savedstate:$version"
            const val compose = "androidx.lifecycle:lifecycle-viewmodel-compose:$version"
            const val service = "androidx.lifecycle:lifecycle-service:$version"
            const val process = "androidx.lifecycle:lifecycle-process:$version"
            const val reactiveStreams = "androidx.lifecycle:lifecycle-reactivestreams-ktx:$version"
            const val test = "androidx.arch.core:core-testing:2.1.0"
        }

        object WorkManager {
            private const val version = "2.8.0-alpha02"
            const val runtime = "androidx.work:work-runtime-ktx:$version"
            const val multiprocess = "androidx.work:work-multiprocess:$version"
            const val test = "androidx.work:work-testing:$version"
        }

        object Room {
            private const val version = "2.5.0-alpha01"
            const val compiler = "androidx.room:room-compiler:$version"
            const val runtime = "androidx.room:room-runtime:$version"
            const val ktx = "androidx.room:room-ktx:$version"
            const val paging = "androidx.room:room-paging:$version"
            const val rxJava3 = "androidx.room:room-rxjava3:$version"
            const val test = "androidx.room:room-testing:$version"
        }

        object DataStore {
            private const val version = "1.0.0"

            object Protobuf {
                const val core = "androidx.datastore:datastore:$version"
                const val rxJava3 = "androidx.datastore:datastore-rxjava3:$version"
            }

            object Preferences {
                const val core = "androidx.datastore:datastore-preferences:$version"
                const val rxJava3 = "androidx.datastore:datastore-preferences-rxjava3:$version"
            }
        }

        object Paging {
            private const val version = "3.1.0"
            const val common = "androidx.paging:paging-common-ktx:$version"
            const val runtime = "androidx.paging:paging-runtime-ktx:$version"
            const val rxJava3 = "androidx.paging:paging-rxjava3:$version"
            const val compose = "androidx.paging:paging-compose:1.0.0-alpha14"
        }

        object Security {
            private const val appAuthenticatorVersion = "1.0.0-alpha02"
            const val biometric = "androidx.biometric:biometric-ktx:1.2.0-alpha04"
            const val crypto = "androidx.security:security-crypto:1.1.0-alpha03"
            const val identity = "androidx.security:security-identity-credential:1.0.0-alpha03"
            const val appAuthenticator = "androidx.security:security-app-authenticator:$appAuthenticatorVersion"
            const val appAuthenticatorTest = "androidx.security:security-app-authenticator:$appAuthenticatorVersion"
        }

        object Media3 {
            private const val version = "1.0.0-alpha03"
            const val core = "androidx.media3:media3-common:$version"
            const val ui = "androidx.media3:media3-ui:$version"
            const val uiTv = "androidx.media3:media3-ui-leanback:$version"
            const val session = "androidx.media3:media3-session:$version"
            const val dataSource = "androidx.media3:media3-datasource:$version"
            const val database = "androidx.media3:media3-database:$version"
            const val decoder = "androidx.media3:media3-decoder:$version"
            const val exoPlayer = "androidx.media3:media3-exoplayer:$version"
            const val workManager = "androidx.media3:media3-exoplayer-workmanager:$version"
            const val transformer = "androidx.media3:media3-transformer:$version"
            const val extractor = "androidx.media3:media3-extractor:$version"
            const val cast = "androidx.media3:media3-cast:$version"
            const val test = "androidx.media3:media3-test-utils:$version"
            const val testRobolectric = "androidx.media3:media3-test-utils-robolectric:$version"

            object ExoPlayer {
                const val dash = "androidx.media3:media3-exoplayer-dash:$version"
                const val hls = "androidx.media3:media3-exoplayer-hls:$version"
                const val rtsp = "androidx.media3:media3-exoplayer-rtsp:$version"
                const val ima = "androidx.media3:media3-exoplayer-ima:$version"
            }

            object DataSource {
                const val cronet = "androidx.media3:media3-datasource-cronet:$version"
                const val okHttp = "androidx.media3:media3-datasource-okhttp:$version"
                const val rtmp = "androidx.media3:media3-datasource-rtmp:$version"
            }
        }

        object Emoji2 {
            private const val version = "1.2.0-alpha03"
            const val core = "androidx.emoji2:emoji2:$version"
            const val views = "androidx.emoji2:emoji2-views:$version"
            const val helper = "androidx.emoji2:emoji2-views-helper:$version"
        }

        object Utils {
            const val annotation = "androidx.annotation:annotation:1.4.0-alpha02"
            const val collection = "androidx.collection:collection-ktx:1.2.0"
            const val documentFile = "androidx.documentfile:documentfile:1.1.0-alpha01"
            const val savedState = "androidx.savedstate:savedstate-ktx:1.2.0-alpha02"
            const val dragAndDrop = "androidx.draganddrop:draganddrop:1.0.0-beta01"
            const val textClassifier = "androidx.textclassifier:textclassifier:1.0.0-alpha04"
            const val sharedTarget = "androidx.sharetarget:sharetarget:1.2.0-rc01"
            const val palette = "androidx.palette:palette-ktx:1.0.0"
        }

        object Instrumentation {
            const val benchmarkVersion = "1.1.0-beta06"
            const val startup = "androidx.startup:startup-runtime:1.2.0-alpha01"
            const val tracing = "androidx.tracing:tracing-ktx:1.1.0-beta01"
            const val metrics = "androidx.metrics:metrics-performance:1.0.0-alpha01"
            const val profileInstaller = "androidx.profileinstaller:profileinstaller:1.2.0-alpha02"
            const val resourceInspection = "androidx.resourceinspection:resourceinspection-annotation:1.0.1"
            const val benchmark = "androidx.benchmark:benchmark-junit4:$benchmarkVersion"
            const val benchmarkMacro = "androidx.benchmark:benchmark-macro-junit4:$benchmarkVersion"
        }

        object Test {
            const val orchestrator = "androidx.test:orchestrator:1.4.2-alpha02"
            const val core = "androidx.test:core-ktx:1.4.1-alpha05"
            const val runner = "androidx.test:runner:1.5.0-alpha02"
            const val monitor = "androidx.test:monitor:1.6.0-alpha05"
            const val rules = "androidx.test:rules:1.4.1-alpha05"
            const val services = "androidx.test.services:test-services:1.4.2-alpha02"
            const val espresso = "androidx.test:espresso:espresso-core:3.5.0-alpha05"
            const val jUnit = "androidx.test.ext:junit-ktx:3.5.0-alpha05"
            const val jUnitTruth = "androidx.test.ext:truth:1.5.0-alpha05"
        }
    }

    object Compose {
        const val version = "1.2.0-alpha07"
        const val compiler = "androidx.compose.compiler:compiler:$version"
        const val runtime = "androidx.compose.runtime:runtime:$version"
        const val liveData = "androidx.compose.runtime:runtime-livedata:$version"
        const val ui = "androidx.compose.ui:ui:$version"
        const val foundation = "androidx.compose.foundation:foundation:$version"
        const val animation = "androidx.compose.animation:animation:$version"
        const val material2 = "androidx.compose.material:material:$version"
        const val material3 = "androidx.compose.material3:material3:1.0.0-alpha09"

        object Glance {
            private const val version = "1.0.0-alpha03"
            const val widget = "androidx.glance:glance-appwidget:$version"
            const val wear = "androidx.glance:glance-wear-tiles:$version"
        }

        object Accompanist {
            private const val version = "0.24.6-alpha"
            const val insets = "com.google.accompanist:accompanist-insets:$version"
            const val insetsUi = "com.google.accompanist:accompanist-insets-ui:$version"
            const val systemUiController = "com.google.accompanist:accompanist-systemuicontroller:$version"
            const val appCompatTheme = "com.google.accompanist:accompanist-appcompat-theme:$version"
            const val pager = "com.google.accompanist:accompanist-pager:$version"
            const val pagerIndicators = "com.google.accompanist:accompanist-pager-indicators:$version"
            const val swipeRefresh = "com.google.accompanist:accompanist-swiperefresh:$version"
            const val placeholder = "com.google.accompanist:accompanist-placeholder:$version"
            const val placeholderMaterial = "com.google.accompanist:accompanist-placeholder-material:$version"
            const val drawablePainter = "com.google.accompanist:accompanist-drawablepainter:$version"
            const val flowLayout = "com.google.accompanist:accompanist-flowlayout:$version"
            const val permissions = "com.google.accompanist:accompanist-permissions:$version"
            const val navigationAnimation = "com.google.accompanist:accompanist-navigation-animation:$version"
            const val navigationMaterial = "com.google.accompanist:accompanist-navigation-material:$version"
            const val webView = "com.google.accompanist:accompanist-webview:$version"
        }

        object ThirdParty {
            const val reorderable = "org.burnoutcrew.composereorderable:reorderable:0.7.4"
        }
    }

    object Dagger {
        private const val version = "2.41"
        const val compiler = "com.google.dagger:dagger-compiler:$version"
        const val androidCompiler = "com.google.dagger:dagger-android-processor:$version"
        const val android = "com.google.dagger:dagger-android:$version"

        object Hilt {
            const val gradle = "com.google.dagger:hilt-android-gradle-plugin:$version"
            const val compiler = "com.google.dagger:hilt-compiler:$version"
            const val android = "com.google.dagger:hilt-android:$version"
            const val androidTest = "com.google.dagger:hilt-android-testing:$version"
        }

        object AndroidX {
            private const val version = "1.0.0"
            const val core = "androidx.hilt:hilt-common:$version"
            const val compiler = "androidx.hilt:hilt-compiler:$version"
            const val navigation = "androidx.hilt:hilt-navigation:$version"
            const val fragment = "androidx.hilt:hilt-navigation-fragment:$version"
            const val workManager = "androidx.hilt:hilt-work:$version"
            const val compose = "androidx.hilt:hilt-navigation-compose:$version"
        }
    }

    object Data {
        const val store4 = "com.dropbox.mobile.store:store4:4.0.5"
    }

    object Network {
        object Ktor {
            private const val version = "2.0.0"
            const val core = "io.ktor:ktor-client-core:$version"
            const val okHttp = "io.ktor:ktor-client-okhttp:$version"
            const val webSockets = "io.ktor:ktor-client-websockets:$version"
            const val test = "io.ktor:ktor-client-mock:$version"

            object Serialization {
                const val json = "io.ktor:ktor-serialization-kotlinx-json:$version"
                const val xml = "io.ktor:ktor-serialization-kotlinx-xml:$version"
            }

            object Extensions {
                const val clientResources = "io.ktor:ktor-client-resources:$version"
                const val contentNegotiation = "io.ktor:ktor-client-content-negotiation:$version"
                const val encoding = "io.ktor:ktor-client-encoding:$version"
                const val authentication = "io.ktor:ktor-client-auth:$version"
                const val logging = "io.ktor:ktor-client-logging:$version"
            }
        }

        object Coil {
            private const val version = "2.0.0-rc03"
            const val core = "io.coil-kt:coil:$version"
            const val compose = "io.coil-kt:coil-compose:$version"
            const val gif = "io.coil-kt:coil-gif:$version"
            const val svg = "io.coil-kt:coil-svg:$version"
            const val video = "io.coil-kt:coil-video:$version"
        }
    }

    object Utils {
        const val timber = "com.jakewharton.timber:timber:5.0.1"
        const val leakCanary = "com.squareup.leakcanary:leakcanary-android:2.8.1"
    }

    object Tests {
        const val jUnit = "junit:junit:4.13.2"
    }
}
