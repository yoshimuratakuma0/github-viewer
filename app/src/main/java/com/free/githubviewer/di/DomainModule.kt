package com.free.githubviewer.di

import android.content.Context
import com.free.data.datasources.GithubApi
import com.free.data.datasources.GithubApiImpl
import com.free.data.datasources.MockGithubApi
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
    fun githubApi(
        @ApplicationContext context: Context
    ): GithubApi =
        if (!BuildConfig.DEBUG) {
            MockGithubApi(context)
        } else {
            GithubApiImpl()
        }

    @Provides
    @Singleton
    @UsersRepositoryAnnotation
    fun usersRepository(
        @ApplicationContext context: Context,
        api: GithubApi
    ): UsersRepository = UsersRepositoryImpl(api)
}