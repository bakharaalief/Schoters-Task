package com.bakharaalief.schoterstask.data.local.room

import androidx.lifecycle.LiveData
import androidx.room.*
import com.bakharaalief.schoterstask.data.local.entity.NewsEntity

@Dao
interface NewsDao {
    @Query("SELECT * from news")
    fun getNews(): LiveData<List<NewsEntity>>

    @Query("SELECT EXISTS(SELECT * FROM news WHERE title = :title)")
    fun searchBookmarkedNews(title: String): LiveData<Boolean>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertNews(news: NewsEntity)

    @Delete
    suspend fun deleteNews(news: NewsEntity)
}