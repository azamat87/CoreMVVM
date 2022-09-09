package com.app.coremvvm.data

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    abstract class Abstract(
        private val converterFactory: ProvideConverterFactory,
        private val okHttpClientBuilder: ProvideOkHttpClientBuilder
    ) : ProvideRetrofitBuilder {

        override fun provideRetrofitBuilder(): Retrofit.Builder = Retrofit.Builder()
            .addConverterFactory(converterFactory.converterFactory())
            .client(okHttpClientBuilder.httpClientBuilder().build())

    }

    class Base(
        converterFactory: ProvideConverterFactory,
        okHttpClientBuilder: ProvideOkHttpClientBuilder
    ) : Abstract(
        converterFactory,
        okHttpClientBuilder
    )

}
