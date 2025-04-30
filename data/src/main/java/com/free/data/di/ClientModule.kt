package com.free.data.di

import com.free.data.datasources.AuthenticationInterceptor
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object ClientModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }
}