package com.bakharaalief.schoterstask.domain.usecase

import androidx.lifecycle.LiveData
import com.bakharaalief.schoterstask.data.Resource
import com.bakharaalief.schoterstask.domain.model.News
import com.bakharaalief.schoterstask.domain.repository.INewsRepository
import javax.inject.Inject

class NewsUseCaseImpl @Inject constructor(private val newsRepository: INewsRepository) :
    NewsUseCase {

    override fun getNews(): LiveData<Resource<List<News>>> = newsRepository.getNews()

    override fun getBookmarkedNews(): LiveData<List<News>> = newsRepository.getBookmarkedNews()

    override fun isNewsBookmarked(title: String): LiveData<Boolean> =
        newsRepository.isNewsBookmarked(title)

    override suspend fun saveNews(news: News) = newsRepository.saveNews(news)

    override suspend fun deleteNews(news: News) = newsRepository.deleteNews(news)
}