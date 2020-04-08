package br.com.fleury.commons.sensedia.di

import android.content.Context
import android.os.Environment
import br.com.fleury.commons.api.LiveDataCallAdapterFactory
import br.com.fleury.commons.sensedia.ISensediaConfigurations
import br.com.fleury.commons.sensedia.SensediaConfigurations
import br.com.fleury.commons.sensedia.api.ISensediaOAuthApi
import br.com.fleury.commons.sensedia.api.interceptors.SensediaAuthenticator
import br.com.fleury.commons.sensedia.api.interceptors.SensediaTokenInterceptor
import br.com.fleury.commons.sensedia.repository.SensediaRepository
import br.com.fleury.commons.sensedia.repository.contracts.ISensediaRepository
import com.facebook.stetho.okhttp3.StethoInterceptor
import com.google.gson.Gson
import com.readystatesoftware.chuck.ChuckInterceptor
import dagger.Module
import dagger.Provides
import okhttp3.Cache
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Named
import javax.inject.Singleton

@Module
open class SensediaModule {

    @Singleton
    @Provides
    @Named("sensedia_cached")
    open fun provideSensediaOkHttpClient(
        sensediaConfigurations: ISensediaConfigurations,
        repository: ISensediaRepository,
        gson: Gson,
        context: Context
    ): OkHttpClient {
        val cache = Cache(Environment.getDownloadCacheDirectory(), 10 * 1024 * 1024)
        return OkHttpClient.Builder()
            .addInterceptor(ChuckInterceptor(context))
            .authenticator(SensediaAuthenticator.create(sensediaConfigurations, repository, gson))
            .addNetworkInterceptor(
                SensediaTokenInterceptor.create
                    (
                    sensediaConfigurations,
                    repository,
                    gson
                )
            )
            .addNetworkInterceptor(StethoInterceptor())
            .connectTimeout(1, TimeUnit.MINUTES)
            .readTimeout(1, TimeUnit.MINUTES)
            .writeTimeout(1, TimeUnit.MINUTES)
            .cache(cache)
            .build()
    }

    @Singleton
    @Provides
    @Named("sensedia")
    open fun provideSensediaRetrofit(
        gson: Gson,
        @Named("sensedia_cached") client: OkHttpClient,
        sensediaConfigurations: ISensediaConfigurations
    ): Retrofit =
        Retrofit.Builder()
            .client(client)
            .baseUrl(sensediaConfigurations.baseUrl)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .addCallAdapterFactory(LiveDataCallAdapterFactory())
            .build()

    @Singleton
    @Provides
    open fun providesSensediaOAuthApi(@Named("sensedia") retrofit: Retrofit): ISensediaOAuthApi =
        retrofit.create(ISensediaOAuthApi::class.java)

    @Singleton
    @Provides
    open fun providesSensediaConfigurations(context: Context): ISensediaConfigurations =
        SensediaConfigurations(context)

    @Singleton
    @Provides
    open fun providesSensediaRepository(): ISensediaRepository = SensediaRepository()
}