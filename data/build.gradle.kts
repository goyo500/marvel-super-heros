import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id(Plugins.androidLibrary)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.daggerHilt)
}

val publicKey: String = gradleLocalProperties(rootDir).getProperty("publicApiKey")
val privateKey: String = gradleLocalProperties(rootDir).getProperty("privateApiKey")

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)


        testInstrumentationRunner = Libraries.testRunner

        buildConfigField("String", "PUBLIC_KEY", publicKey)
        buildConfigField("String", "PRIVATE_KEY", privateKey)
    }

    buildTypes {
        named("release").configure {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }

        named("debug").configure {
            isMinifyEnabled = true
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }


    }

    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
        getByName("test") { java.srcDir("src/test/kotlin") }
        getByName("androidTest") { java.srcDir("src/androidTest/kotlin") }
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
    implementation(Libraries.kotlinCoroutinesCore)
    implementation(Libraries.retrofitCoroutinesAdapter)

    // other modules
    implementation(project(":domain"))

    // 3rd party libraries
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)
    implementation(Libraries.retrofit)
    implementation(Libraries.retrofitMoshiConverter)
    implementation(Libraries.moshi)
    implementation(Libraries.moshiKotlin)
    implementation(Libraries.interceptor)
    kapt(Libraries.moshiKotlinCodegen)

    //room
    implementation(Libraries.androidxRoom)
    implementation(Libraries.androidxRoomCoroutines)
    kapt(Libraries.androidxRoomcompiler)

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