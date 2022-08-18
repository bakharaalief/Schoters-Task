package com.bakharaalief.schoterstask.di

import android.content.Context
import androidx.room.Room
import com.bakharaalief.schoterstask.data.local.room.NewsDB
import com.bakharaalief.schoterstask.data.local.room.NewsDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class DatabaseModule {

    @Singleton
    @Provides
    fun provideDatabase(@ApplicationContext context: Context): NewsDB =
        Room.databaseBuilder(context, NewsDB::class.java, "News.db")
            .fallbackToDestructiveMigration()
            .build()

    @Provides
    fun provideNewsDao(database: NewsDB): NewsDao = database.newsDao()

}