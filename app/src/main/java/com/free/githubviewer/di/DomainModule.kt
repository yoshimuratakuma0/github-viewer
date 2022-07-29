package com.free.githubviewer.di

import com.free.data.datasources.GithubApi
import com.free.data.datasources.GithubApiImpl
import com.free.data.repositories.UsersRepositoryImpl
import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.GetUserDetailUseCase
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
    fun githubApi(): GithubApi = GithubApiImpl()

    @Provides
    @Singleton
    fun usersRepository(
        api: GithubApi
    ): UsersRepository = UsersRepositoryImpl(api)

    @Provides
    fun provideFetchUsersUseCase(repository: UsersRepository): FetchUsersUseCase {
        return FetchUsersUseCase(repository)
    }

    @Provides
    fun provideGetUserDetailUseCase(repository: UsersRepository): GetUserDetailUseCase {
        return GetUserDetailUseCase(repository)
    }
}