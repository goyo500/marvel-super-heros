plugins {
    id(Plugins.androidApplication)
    kotlin(Plugins.kotlinAndroid)
    kotlin(Plugins.kotlinAndroidExtensions)
    kotlin(Plugins.kotlinKapt)
    id(Plugins.safeArgs)
    id(Plugins.daggerHilt)
}

android {
    compileSdkVersion(AndroidSdk.compile)

    defaultConfig {
        applicationId = "es.plexus.android.marvelsuperheroes"
        minSdkVersion(AndroidSdk.min)
        targetSdkVersion(AndroidSdk.target)
        versionCode = CodeVersion.code
        versionName = CodeVersion.name
        multiDexEnabled = true

    }

    signingConfigs {
        register("release") {
        }
    }
    buildTypes {
        named("release").configure {
            isMinifyEnabled = false
            signingConfig = signingConfigs.getByName("release")
            proguardFiles(
                getDefaultProguardFile("proguard-android-optimize.txt"),
                "proguard-rules.pro"
            )
        }
    }
    sourceSets {
        getByName("main") { java.srcDir("src/main/kotlin") }
    }
    lintOptions {
        isAbortOnError = false
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

    // other modules
    implementation(project(":presentation"))
    implementation(project(":domain"))
    implementation(project(":data"))

    // 3rd party libraries
    implementation(Libraries.hiltAndroid)
    kapt(Libraries.hiltCompiler)

}
