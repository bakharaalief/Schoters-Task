package com.bakharaalief.schoterstask.di

import com.bakharaalief.schoterstask.data.NewsRepository
import com.bakharaalief.schoterstask.domain.repository.INewsRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module(includes = [NetworkModule::class, DatabaseModule::class])
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideNewsRepository(newsRepository: NewsRepository): INewsRepository
}