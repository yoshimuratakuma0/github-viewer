package com.free.githubviewer.di

import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Provides
    fun provideFetchUsersUseCase(
        repository: UsersRepository,
        dispatcher: CoroutineDispatcher,
    ): FetchUsersUseCase {
        return FetchUsersUseCase(repository, dispatcher)
    }

    @Provides
    fun provideGetUserDetailUseCase(
        repository: UsersRepository,
        dispatcher: CoroutineDispatcher,
    ): GetUserDetailUseCase {
        return GetUserDetailUseCase(repository)
    }
}