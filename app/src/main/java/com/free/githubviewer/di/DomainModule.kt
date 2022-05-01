package com.free.githubviewer.di

import android.content.Context
import com.free.data.datasources.GithubClient
import com.free.data.repositories.MockUsersRepositoryImpl
import com.free.data.repositories.UsersRepositoryImpl
import com.free.domain.di.UsersRepositoryAnnotation
import com.free.domain.repositories.UsersRepository
import com.free.githubviewer.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


@InstallIn(SingletonComponent::class)
@Module
object DomainModule {

    @Provides
    @Singleton
    fun githubClient(): GithubClient {
        return GithubClient()
    }

    @Provides
    @Singleton
    @UsersRepositoryAnnotation
    fun usersRepository(
        @ApplicationContext context: Context,
        client: GithubClient
    ): UsersRepository = if (BuildConfig.DEBUG) {
        MockUsersRepositoryImpl(context)
    } else {
        UsersRepositoryImpl(client)
    }
}