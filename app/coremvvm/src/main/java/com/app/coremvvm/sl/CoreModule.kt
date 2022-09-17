package com.app.coremvvm.sl

import android.content.Context
import android.content.SharedPreferences
import com.app.coremvvm.BuildConfig
import com.app.coremvvm.core.Dispatchers
import com.app.coremvvm.core.ManageResources
import com.app.coremvvm.data.ProvideConverterFactory
import com.app.coremvvm.data.ProvideInterceptor
import com.app.coremvvm.data.ProvideOkHttpClientBuilder
import com.app.coremvvm.data.ProvideRetrofitBuilder
import com.app.coremvvm.presentation.CanGoBack
import com.app.coremvvm.presentation.GlobalErrorCommunication
import com.app.coremvvm.presentation.ProgressCommunication
import retrofit2.Retrofit

interface CoreModule : ManageResources, ProvideDispatchers, ProvideGlobalErrorCommunication,
    ProvideProgressCommunication, ProvideRetrofitBuilder, SharedPref{

    class Base(private val context: Context) : CoreModule {
        private val dispatchers = Dispatchers.Base()
        private val manageResources = ManageResources.Base(context)

        private val communication = GlobalErrorCommunication.Base()
        private val progress = ProgressCommunication.Base()

        private val retrofitBuilder = ProvideRetrofitBuilder.Base(
            ProvideConverterFactory.Base(),
            ProvideOkHttpClientBuilder.Base(
                if (BuildConfig.DEBUG)
                    ProvideInterceptor.Debug()
                else
                    ProvideInterceptor.Release()
            )
        )

        override fun string(id: Int): String = manageResources.string(id)

        override fun dispatchers(): Dispatchers = dispatchers

        override fun provideGlobalErrorCommunication(): GlobalErrorCommunication.Mutable = communication

        override fun provideProgressCommunication(): ProgressCommunication.Mutable = progress

        override fun provideRetrofitBuilder(): Retrofit.Builder = retrofitBuilder.provideRetrofitBuilder()

        override fun sharedPreferences(key: String): SharedPreferences =
            context.getSharedPreferences(key, Context.MODE_PRIVATE)
    }

}

interface ProvideGlobalErrorCommunication {
    fun provideGlobalErrorCommunication() : GlobalErrorCommunication.Mutable
}

interface ProvideProgressCommunication {
    fun provideProgressCommunication() : ProgressCommunication.Mutable
}

interface SharedPref {
    fun sharedPreferences(key: String) : SharedPreferences
}

interface ProvideCanGoBack {
    fun provideCanGoBack() : CanGoBack.Callback
}

interface ProvideDispatchers{
    fun dispatchers() : Dispatchers
}