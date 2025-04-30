package com.free.data.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Qualifier
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UrlModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return "https://api.github.com/"
    }
}

@Qualifier
@Retention(AnnotationRetention.BINARY)
annotation class BaseUrl