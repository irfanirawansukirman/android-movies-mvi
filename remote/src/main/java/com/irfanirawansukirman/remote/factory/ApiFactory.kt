package com.irfanirawansukirman.remote.factory

import android.content.Context
import com.irfanirawansukirman.remote.BuildConfig
import com.readystatesoftware.chuck.ChuckInterceptor
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import java.util.concurrent.TimeUnit

object ApiFactory {

    fun build(
        application: Context, baseUrl: String
    ): Retrofit {

        val client = OkHttpClient.Builder()
            .retryOnConnectionFailure(true)
            .pingInterval(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .connectTimeout(30, TimeUnit.SECONDS)
            .apply {
                if (BuildConfig.DEBUG) {
                    addInterceptor(getChuckIntercept(application))
                    addInterceptor(getHttpLogIntercept())
                    addInterceptor(getChainIntercept())
                }
            }.build()

        return getRetrofit(client, baseUrl)
    }

    private fun getRetrofit(
        client: OkHttpClient,
        baseUrl: String
    ) = Retrofit.Builder()
        .baseUrl(baseUrl)
        .addConverterFactory(MoshiConverterFactory.create())
        .client(client)
        .build()

    private fun getChuckIntercept(appContext: Context) = ChuckInterceptor(appContext)

    private fun getHttpLogIntercept() =
        HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

    private fun getChainIntercept() = { chain: Interceptor.Chain ->
        chain.proceed(
            chain.request().newBuilder()
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .build()
        )
    }

    inline fun <reified T> getService(retrofit: Retrofit): T = retrofit.create(T::class.java)
}