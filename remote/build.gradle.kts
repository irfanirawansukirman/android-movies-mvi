import com.android.build.gradle.internal.cxx.configure.gradleLocalProperties

plugins {
    id("com.android.library")
    id("kotlin-android")
    id("kotlin-kapt")
    id("kotlin-parcelize")
}

val movieApiBaseUrl: String = gradleLocalProperties(rootDir).getProperty("MOVIE_API_BASE_URL")
val tmdbApiKey: String = gradleLocalProperties(rootDir).getProperty("TMDB_API_KEY")

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

        buildConfigField("String", "MOVIE_API_BASE_URL", "\"" + movieApiBaseUrl + "\"")
        buildConfigField("String", "TMDB_API_KEY", "\"" + tmdbApiKey + "\"")
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
    implementation(project(":core"))

    api(App.retrofit2)
    implementation(App.loggingInterceptor)
    implementation(App.okhttp)

    kapt(App.moshiKotlinCodegen)

    kapt(App.daggerCompiler)

    debugImplementation(App.chuck)
    releaseImplementation(App.chuckNoOp)
}