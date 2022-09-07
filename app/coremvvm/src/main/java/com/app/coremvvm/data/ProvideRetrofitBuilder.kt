package com.app.coremvvm.data

import retrofit2.Retrofit

interface ProvideRetrofitBuilder {

    fun provideRetrofitBuilder(): Retrofit.Builder

    abstract class Abstract(

    ) : ProvideRetrofitBuilder {



    }

}
