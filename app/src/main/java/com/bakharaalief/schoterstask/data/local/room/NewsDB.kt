package com.bakharaalief.schoterstask.data.local.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.bakharaalief.schoterstask.data.local.entity.NewsEntity

@Database(entities = [NewsEntity::class], version = 1, exportSchema = false)
abstract class NewsDB : RoomDatabase() {

    abstract fun newsDao(): NewsDao
}