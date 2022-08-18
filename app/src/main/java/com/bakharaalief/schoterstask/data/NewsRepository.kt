package com.bakharaalief.schoterstask.data

import androidx.lifecycle.LiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.liveData
import com.bakharaalief.schoterstask.data.local.room.NewsDao
import com.bakharaalief.schoterstask.data.remote.retrofit.ApiService
import com.bakharaalief.schoterstask.domain.model.News
import com.bakharaalief.schoterstask.domain.repository.INewsRepository
import com.bakharaalief.schoterstask.util.DataMapper
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class NewsRepository @Inject constructor(
    private val apiService: ApiService,
    private val newsDao: NewsDao
) : INewsRepository {

    private val apiKey = "e5fac7dc0b984f9bb8fd852647ce212b"
    private val country = "us"

    override fun getNews(): LiveData<Resource<List<News>>> = liveData {
        emit(Resource.Loading)

        try {
            val listNews = apiService.getNews(apiKey, country).articles
            emit(Resource.Success(DataMapper.responseToNewsModel(listNews)))
        } catch (e: Exception) {
            emit(Resource.Error(e.message.toString()))
        }
    }

    override fun getBookmarkedNews(): LiveData<List<News>> {
        return Transformations.map(newsDao.getNews()) { DataMapper.newsEntityToNewsModel(it) }
    }

    override fun isNewsBookmarked(title: String): LiveData<Boolean> =
        newsDao.searchBookmarkedNews(title)

    override suspend fun saveNews(news: News) {
        newsDao.insertNews(DataMapper.modelToNewsEntity(news))
    }

    override suspend fun deleteNews(news: News) {
        newsDao.deleteNews(DataMapper.modelToNewsEntity(news))
    }


}