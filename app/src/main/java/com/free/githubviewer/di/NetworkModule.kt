package com.free.githubviewer.di

import com.free.data.datasources.GithubApi
import com.free.data.datasources.GithubApiImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module
object NetworkModule {
    @Provides
    @Singleton
    fun githubApi(): GithubApi = GithubApiImpl()
}