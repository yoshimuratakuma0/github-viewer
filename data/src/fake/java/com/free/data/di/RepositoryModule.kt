package com.free.data.di

import com.free.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface FakeRepositoryModule {

    @Binds
    @Singleton
    fun bindsUsersRepository(
        repository: FakeUsersRepository,
    ): UsersRepository
}