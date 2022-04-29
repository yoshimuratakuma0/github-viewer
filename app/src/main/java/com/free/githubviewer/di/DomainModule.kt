package com.free.githubviewer.di

import com.free.data.datasources.GithubClient
import com.free.data.repositories.UsersRepositoryImpl
import com.free.domain.di.UsersRepositoryAnnotation
import com.free.domain.repositories.UsersRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
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
    fun usersRepository(client: GithubClient): UsersRepository {
        return UsersRepositoryImpl(client)
    }
}