plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
}

android {
    compileSdkVersion(Android.compileSdk)
    buildToolsVersion = Android.buildTools

    defaultConfig {
        minSdkVersion(Android.minSdk)
        targetSdkVersion(Android.targetSdk)
        versionCode = Android.versionCode
        versionName = Android.versionName

        testInstrumentationRunner = "androidx.test.runner.AndroidJUnitRunner"
        consumerProguardFiles("consumer-rules.pro")
    }

    buildTypes {
        getByName("release") {
            isMinifyEnabled = false
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
    tasks.withType<org.jetbrains.kotlin.gradle.tasks.KotlinCompile> {
        kotlinOptions {
            jvmTarget = "1.8"
        }
    }
    lintOptions {
        isAbortOnError = false
    }

    buildFeatures {
        viewBinding = true
    }
}

dependencies {
    api(App.appCompat)
    api(App.fragmentKtx)
    api(App.activityKtx)
    api(App.coreKtx)
    api(App.materialUi)
    api(App.legacySupportV4)

    api(App.moshiKotlin)
    api(App.moshi)
    api(App.converterMoshi)
    kapt(App.moshiKotlinCodegen)

    api(App.coroutinesCore)
    api(App.coroutinesAndroid)

    api(App.dagger)
    kapt(App.daggerCompiler)

    api(App.glide)
    kapt(App.glideCompiler)

    api(App.runtimeKtx)
    api(App.lifecycleCommonJava8)
    api(App.viewModelKtx)

    api(platform(App.firebaseBom))
    api(App.crashlyticsKtx)
    api(App.analyticsKtx)
}