package com.mezmeraiz.blank.di.app

import com.mezmeraiz.blank.TIMEOUT
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.CallAdapter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit
import javax.inject.Singleton
import com.google.gson.GsonBuilder
import com.google.gson.Gson
import com.mezmeraiz.blank.BASE_URL

@Module
class NetworkModule() {

    @Singleton
    @Provides
    fun provideRetrofit(client: OkHttpClient,
                        gsonConverterFactory: GsonConverterFactory,
                        callAdapterFactory: CallAdapter.Factory): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(gsonConverterFactory)
            .addCallAdapterFactory(callAdapterFactory)
            .client(client)
            .build()
    }

    @Provides
    fun provideClient(): OkHttpClient {
        return OkHttpClient.Builder()
                .addInterceptor { chain ->
                    val original = chain!!.request()
                    val request = original.newBuilder()
                            .build()
                    chain.proceed(request)
                }
                .readTimeout(TIMEOUT, TimeUnit.SECONDS)
                .connectTimeout(TIMEOUT, TimeUnit.SECONDS)
                .build()
    }

    @Provides
    fun provideConverterFactory(gson: Gson): GsonConverterFactory{
        return GsonConverterFactory.create(gson)
    }

    @Provides
    fun provideCallAdapterFactory(): CallAdapter.Factory{
        return RxJava2CallAdapterFactory.create()
    }

    @Provides
    fun provideGson(builder: GsonBuilder): Gson {
        return builder.excludeFieldsWithoutExposeAnnotation().create()
    }

    @Provides
    fun provideGsonBuilder(): GsonBuilder {
        return GsonBuilder()
    }

}