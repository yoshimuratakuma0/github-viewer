package com.free.presentation.views

import com.free.data.di.RepositoryModule
import com.free.domain.repositories.UsersRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn

@Module
@TestInstallIn(
    components = [SingletonComponent::class],
    replaces = [RepositoryModule::class]
)
interface FakeRepositoryModule {
    @Binds
    fun bindsUsersRepository(
        repository: FakeUsersRepository,
    ): UsersRepository
}
