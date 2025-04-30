package com.free.data

import com.free.data.di.BaseUrl
import com.free.data.di.UrlModule
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import javax.inject.Singleton

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [UrlModule::class]
)
object FakeUrlModule {

    @Provides
    @Singleton
    @BaseUrl
    fun provideBaseUrl(): String {
        return "http://localhost:8080/"
    }
}