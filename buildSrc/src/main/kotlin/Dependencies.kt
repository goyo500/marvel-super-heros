const val kotlinVersion = "1.7.10"

object Build {
    object Versions {
        const val gradle = "4.3.14"
        const val safeArgs = "2.5.2"
    }

    const val androidGradlePlugin = "com.android.tools.build:gradle:${Versions.gradle}"
    const val kotlinGradlePlugin = "org.jetbrains.kotlin:kotlin-gradle-plugin:$kotlinVersion"
    const val navigationSafeArgs = "androidx.navigation:navigation-safe-args-gradle-plugin:${Versions.safeArgs}"

}

object Plugins {
    const val androidApplication = "com.android.application"
    const val androidLibrary = "com.android.library"
    const val kotlinAndroid = "android"
    const val kotlinAndroidExtensions = "android.extensions"
    const val version = "version.gradle.kts"
    const val kotlinKapt = "kapt"
    const val safeArgs = "androidx.navigation.safeargs"
}

object AndroidSdk {
    const val min = 21
    const val compile = 33
    const val target = compile
}

object CodeVersion {
    const val code = 1
    const val name = "1.0"
}

// librarias
object Libraries {
    // kotlin
    const val kotlinStdLib = "org.jetbrains.kotlin:kotlin-stdlib:${Versions.stdLib}"
    const val kotlinCoroutinesCore =
        "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Versions.coroutines}"
    const val kotlinCoroutinesAndroid =
        "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Versions.coroutines}"
    const val arrowCore = "io.arrow-kt:arrow-core:${Versions.arrow}"
    const val arrowSyntax = "io.arrow-kt:arrow-syntax:${Versions.arrow}"
    const val arrowMeta = "io.arrow-kt:arrow-meta:${Versions.arrow}"


    // androidx
    const val appCompat = "androidx.appcompat:appcompat:${Versions.appCompat}"
    const val lifecycle = "androidx.lifecycle:lifecycle-runtime-ktx:${Versions.lifecycle}"
    const val viewModelKtx = "androidx.lifecycle:lifecycle-viewmodel-ktx:${Versions.lifecycle}"
    const val constraintLayout =
        "androidx.constraintlayout:constraintlayout:${Versions.constraintLayout}"
    const val recyclerview = "androidx.recyclerview:recyclerview:${Versions.recyclerView}"
    const val cardview = "androidx.cardview:cardview:${Versions.cardView}"
    const val dxCore = "androidx.core:core-ktx:${Versions.coreDx}"
    const val androidxRoom = "androidx.room:room-runtime:${Versions.room}"
    const val androidxRoomCoroutines = "androidx.room:room-ktx:${Versions.room}"
    const val androidxRoomcompiler = "androidx.room:room-compiler:${Versions.room}"

    // Kotlin
    const val navigationFragment =
        "androidx.navigation:navigation-fragment-ktx:${Versions.navigation}"
    const val navigationUiKtx = "androidx.navigation:navigation-ui-ktx:${Versions.navigation}"

    // google
    const val googleMaterial = "com.google.android.material:material:${Versions.googleMaterial}"

    // koin
    const val koinAndroid = "io.insert-koin:koin-android:${Versions.koin}"

    // retrofit
    const val retrofitCoroutinesAdapter =
        "com.jakewharton.retrofit:retrofit2-kotlin-coroutines-adapter:${Versions.coroutinesAdapter}"
    const val retrofit = "com.squareup.retrofit2:retrofit:${Versions.retrofit}"
    const val retrofitMoshiConverter = "com.squareup.retrofit2:converter-moshi:${Versions.retrofit}"
    const val moshi = "com.squareup.moshi:moshi:${Versions.moshi}"
    const val moshiKotlin = "com.squareup.moshi:moshi-kotlin:${Versions.moshi}"
    const val moshiKotlinCodegen = "com.squareup.moshi:moshi-kotlin-codegen:${Versions.moshi}"
    const val interceptor = "com.squareup.okhttp3:logging-interceptor:${Versions.interceptor}"

    //ui
    const val lottie = "com.airbnb.android:lottie:${Versions.lottie}"
    const val glide = "com.github.bumptech.glide:glide:${Versions.glide}"

    // testing
    const val junit = "junit:junit:${Versions.junit}"
    const val mockitoKotlin = "com.nhaarman.mockitokotlin2:mockito-kotlin:${Versions.mockitoKotlin}"
    const val kotlinCoroutinesTest =
        "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Versions.coroutines}"
    const val koinTest = "io.insert-koin:koin-test:${Versions.koin}"
    const val mockitoAndroid = "org.mockito:mockito-android:${Versions.mockitoAndroid}"
    const val testRunner = "androidx.test:runner:${Versions.androidxTestRunner}"
    const val testRules = "androidx.test:rules:${Versions.androidxTestRules}"

    private object Versions {
        // core & kotlin
        const val coroutines = "1.6.4"
        const val coroutinesAdapter = "0.9.2"
        const val stdLib = "1.7.10"

        // androidx
        const val appCompat = "1.6.0-alpha05"
        const val lifecycle = "2.6.0-alpha02"
        const val constraintLayout = "2.0.4"
        const val recyclerView = "1.1.0"
        const val cardView = "1.0.0"
        const val coreDx = "1.9.0"
        const val room = "2.5.0-alpha03"
        const val navigation = "2.4.0-alpha04"

        // 3rd party
        const val googleMaterial = "1.4.0"
        const val koin = "3.2.1"
        const val arrow = "0.11.0"
        const val retrofit = "2.8.1"
        const val moshi = "1.9.3"
        const val interceptor = "4.9.0"
        const val lottie = "3.7.1"
        const val glide = "4.11.0"

        // test
        const val junit = "4.13"
        const val androidxTestRunner = "1.3.0"
        const val androidxTestRules = "1.3.0"
        const val mockitoAndroid = "3.2.4"
        const val mockitoKotlin = "2.1.0"
    }
}