package com.free.githubviewer.di

import com.free.domain.repositories.UsersRepository
import com.free.domain.usecases.FetchFollowersUseCase
import com.free.domain.usecases.FetchFollowingUseCase
import com.free.domain.usecases.FetchUsersUseCase
import com.free.domain.usecases.GetUserDetailUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object UseCaseModule {
    @Singleton
    @Provides
    fun provideFetchUsersUseCase(
        repository: UsersRepository,
        dispatcher: CoroutineDispatcher,
    ): FetchUsersUseCase {
        return FetchUsersUseCase(repository, dispatcher)
    }

    @Singleton
    @Provides
    fun provideFetchFollowingUseCase(
        repository: UsersRepository,
        dispatcher: CoroutineDispatcher,
    ): FetchFollowingUseCase {
        return FetchFollowingUseCase(repository, dispatcher)
    }

    @Singleton
    @Provides
    fun provideFetchFollowersUseCase(
        repository: UsersRepository,
        dispatcher: CoroutineDispatcher,
    ): FetchFollowersUseCase {
        return FetchFollowersUseCase(repository, dispatcher)
    }

    @Singleton
    @Provides
    fun provideGetUserDetailUseCase(
        repository: UsersRepository,
        dispatcher: CoroutineDispatcher,
    ): GetUserDetailUseCase {
        return GetUserDetailUseCase(repository, dispatcher)
    }
}