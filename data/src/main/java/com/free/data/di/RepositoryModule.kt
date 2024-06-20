package com.free.data.di

import com.free.data.repositories.UsersRepositoryImpl
import com.free.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
interface RepositoryModule {

    @Binds
    @Singleton
    fun bindsUsersRepository(
        api: UsersRepositoryImpl,
    ): UsersRepository
}