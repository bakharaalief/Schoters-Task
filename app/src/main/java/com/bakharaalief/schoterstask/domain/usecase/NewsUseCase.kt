package com.bakharaalief.schoterstask.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.schoterstask.data.Resource
import com.bakharaalief.schoterstask.domain.model.News

interface NewsUseCase {
    fun getNews(): LiveData<Resource<List<News>>>

    fun getBookmarkedNews(): LiveData<List<News>>

    fun isNewsBookmarked(title: String): LiveData<Boolean>

    suspend fun saveNews(news: News)

    suspend fun deleteNews(news: News)
}