package com.free.githubviewer.di

import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    fun provideFetchUsersUseCase(repository: UsersRepository): FetchUsersUseCase {
        return FetchUsersUseCase(repository)
    }

    @Provides
    fun provideGetUserDetailUseCase(repository: UsersRepository): GetUserDetailUseCase {
        return GetUserDetailUseCase(repository)
    }
}