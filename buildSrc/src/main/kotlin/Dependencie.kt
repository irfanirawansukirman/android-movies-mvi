object Plugin {
    val android by lazy { "com.android.tools.build:gradle:${Version.gradlePlugin}" }
    val kotlin by lazy { "org.jetbrains.kotlin:kotlin-gradle-plugin:${Version.kotlin}" }
    val googleService by lazy { "com.google.gms:google-services:${Version.googleService}" }
    val firebaseCrashlytics by lazy { "com.google.firebase:firebase-crashlytics-gradle:${Version.firebaseCrashlytics}" }
}

object App {
    val appCompat by lazy { "androidx.appcompat:appcompat:${Version.appCompat}" }
    val fragmentKtx by lazy { "androidx.fragment:fragment-ktx:${Version.fragmentKtx}" }
    val activityKtx by lazy { "androidx.activity:activity-ktx:${Version.activityKtx}" }
    val coreKtx by lazy { "androidx.core:core-ktx:${Version.coreKtx}" }
    val materialUi by lazy { "com.google.android.material:material:${Version.materialUi}" }
    val legacySupportV4 by lazy { "androidx.legacy:legacy-support-v4:${Version.legacySupportV4}" }
    val constraintLayout by lazy { "androidx.constraintlayout:constraintlayout:${Version.constraintLayout}" }

    val coroutinesCore by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-core:${Version.coroutinesCore}" }
    val coroutinesAndroid by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-android:${Version.coroutinesAndroid}" }

    val retrofit2 by lazy { "com.squareup.retrofit2:retrofit:${Version.retrofit2}" }
    val loggingInterceptor by lazy { "com.squareup.okhttp3:logging-interceptor:${Version.loggingInterceptor}" }
    val okhttp by lazy { "com.squareup.okhttp3:okhttp:${Version.okHttp}" }

    val roomRuntime by lazy { "androidx.room:room-runtime:${Version.roomRuntime}" }
    val roomCompiler by lazy { "androidx.room:room-compiler:${Version.roomCompiler}" }
    val roomKtx by lazy { "androidx.room:room-ktx:${Version.roomKtx}" }
    val securityCrypto by lazy { "androidx.security:security-crypto:${Version.securityCrypto}" }

    val moshiKotlin by lazy { "com.squareup.moshi:moshi-kotlin:${Version.moshiKotlin}" }
    val moshi by lazy { "com.squareup.moshi:moshi:${Version.moshi}" }
    val converterMoshi by lazy { "com.squareup.retrofit2:converter-moshi:${Version.converterMoshi}" }
    val moshiKotlinCodegen by lazy { "com.squareup.moshi:moshi-kotlin-codegen:${Version.moshiKotlinCodegen}" }

    val dagger by lazy { "com.google.dagger:dagger:${Version.dagger}" }
    val daggerCompiler by lazy { "com.google.dagger:dagger-compiler:${Version.daggerCompiler}" }

    val glide by lazy { "com.github.bumptech.glide:glide:${Version.glide}" }
    val glideCompiler by lazy { "com.github.bumptech.glide:compiler:${Version.glideCompiler}" }

    val coil by lazy { "io.coil-kt:coil:${Version.coil}" }

    val runtimeKtx by lazy { "androidx.lifecycle:lifecycle-runtime-ktx:${Version.runtimeKtx}" }
    val lifecycleCommonJava8 by lazy { "androidx.lifecycle:lifecycle-common-java8:${Version.lifecycleCommonJava8}" }
    val viewModelKtx by lazy { "androidx.lifecycle:lifecycle-viewmodel-ktx:${Version.viewModelKtx}" }

    val chuck by lazy { "com.readystatesoftware.chuck:library:${Version.chuck}" }
    val chuckNoOp by lazy { "com.readystatesoftware.chuck:library-no-op:${Version.chuck}" }
    val stetho by lazy { "com.facebook.stetho:stetho:${Version.stetho}" }

    val firebaseBom by lazy { "com.google.firebase:firebase-bom:${Version.firebaseBom}" }
    val crashlyticsKtx by lazy { "com.google.firebase:firebase-crashlytics-ktx" }
    val analyticsKtx by lazy { "com.google.firebase:firebase-analytics-ktx" }

    val junit by lazy { "junit:junit:${Version.junit}" }
    val mockk by lazy { "io.mockk:mockk:${Version.mockk}" }
    val coroutinesTest by lazy { "org.jetbrains.kotlinx:kotlinx-coroutines-test:${Version.coroutinesTest}" }
    val coreTesting by lazy { "androidx.arch.core:core-testing:${Version.coreTesting}" }
    val junitExt by lazy { "androidx.test.ext:junit:${Version.junitExt}" }
    val espressoCore by lazy { "androidx.test.espresso:espresso-core:${Version.espressoCore}" }
    val espressoIntents by lazy { "androidx.test.espresso:espresso-intents:${Version.espressoIntents}" }
    val testRunner by lazy { "androidx.test:runner:${Version.testRunner}" }
    val testRules by lazy { "androidx.test:rules:${Version.testRules}" }
    val roomTesting by lazy { "androidx.room:room-testing:${Version.roomTesting}" }
    val googleTruth by lazy { "com.google.truth:truth:${Version.googleTruth}" }
    val turbine by lazy { "app.cash.turbine:turbine:${Version.turbine}" }
}