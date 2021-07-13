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
}

dependencies {
    implementation(App.roomRuntime)
    kapt(App.roomCompiler)
    implementation(App.roomKtx)

    implementation(App.dagger)
    kapt(App.daggerCompiler)

    implementation(App.securityCrypto)

    testImplementation(App.junit)

    androidTestImplementation(App.roomTesting)
    androidTestImplementation(App.coreTesting)
    androidTestImplementation(App.espressoCore)
    androidTestImplementation(App.junitExt)
}