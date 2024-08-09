package com.free.data.di

import com.free.data.datasources.AuthenticationInterceptor
import com.free.data.datasources.GithubApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.kotlinx.serialization.asConverterFactory
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {

    @Provides
    @Singleton
    fun provideAuthenticationInterceptor(): AuthenticationInterceptor {
        return AuthenticationInterceptor()
    }

    @Provides
    @Singleton
    fun provideOkHttpClient(
        interceptor: AuthenticationInterceptor,
    ): OkHttpClient {
        return OkHttpClient().newBuilder().addInterceptor(interceptor).build()
    }


    @Provides
    @Singleton
    fun provideGithubApi(
        okHttpClient: OkHttpClient,
    ): GithubApi {
        val format = Json { ignoreUnknownKeys = true }
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl(GithubApi.BASE_URL)
            .addConverterFactory(format.asConverterFactory("application/json".toMediaType()))
            .build()
            .create(GithubApi::class.java)
    }
}