package com.app.coremvvm.data

import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

interface ProvideOkHttpClientBuilder {

    fun httpClientBuilder() : OkHttpClient.Builder

    abstract class Abstract(
        private val provideInterceptor: ProvideInterceptor,
        private val timeOfSeconds: Long = 60L
    ) : ProvideOkHttpClientBuilder {

        override fun httpClientBuilder(): OkHttpClient.Builder =
            OkHttpClient.Builder()
                .addInterceptor(provideInterceptor.interceptor())
                .connectTimeout(timeOfSeconds, TimeUnit.SECONDS)
                .readTimeout(timeOfSeconds, TimeUnit.SECONDS)

    }

    class Base(
        private val provideInterceptor: ProvideInterceptor
        ): Abstract(provideInterceptor)

}