plugins {
    id(Plugins.androidLibrary)

    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        testInstrumentationRunner = Libraries.testRunner
    }

    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
        getByName("test") { java.srcDir("src/test/kotlin") }
        getByName("androidTest") { java.srcDir("src/androidTest/kotlin") }
    }

    buildFeatures {
        viewBinding = true
    }
    lintOptions {
        isAbortOnError = false
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    compileOptions {
        sourceCompatibility = JavaVersion.VERSION_1_8
        targetCompatibility = JavaVersion.VERSION_1_8
    }
    kotlinOptions {
        jvmTarget = JavaVersion.VERSION_1_8.toString()
    }
}

dependencies {

    implementation(Libraries.kotlinStdLib)
    implementation(Libraries.appCompat)
    implementation(Libraries.lifecycle)
    implementation(Libraries.viewModelKtx)
    implementation(Libraries.constraintLayout)
    implementation(Libraries.recyclerview)
    implementation(Libraries.cardview)
    implementation(Libraries.googleMaterial)
    implementation(Libraries.navigationFragment)
    implementation(Libraries.navigationUiKtx)


    // other modules
    implementation(project(":domain"))

    // 3rd party libraries
    implementation(Libraries.koinAndroid)
    implementation(Libraries.lottie)
    implementation(Libraries.glide)

    // testing dependencies - Unit Test
    testImplementation(Libraries.junit)
    testImplementation(Libraries.mockitoKotlin)
    testImplementation(Libraries.kotlinCoroutinesTest)

    // koin testing tools
    testImplementation(Libraries.koinTest)

    // testing dependencies - Instrumentation Test
    androidTestImplementation(Libraries.mockitoAndroid)
    androidTestImplementation(Libraries.testRunner)
    androidTestImplementation(Libraries.testRules)

    // koin testing tools
    androidTestImplementation(Libraries.koinTest) {
        exclude("group", "org.mockito")
        exclude("group", "mockito-inline")
    }
}