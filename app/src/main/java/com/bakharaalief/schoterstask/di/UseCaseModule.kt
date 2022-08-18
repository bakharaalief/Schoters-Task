package com.bakharaalief.schoterstask.di

import com.bakharaalief.schoterstask.domain.usecase.NewsUseCase
import com.bakharaalief.schoterstask.domain.usecase.NewsUseCaseImpl
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
abstract class UseCaseModule {

    @Binds
    @ViewModelScoped
    abstract fun provideNewsUseCase(newsUseCaseImpl: NewsUseCaseImpl): NewsUseCase
}