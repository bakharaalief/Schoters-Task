package com.bakharaalief.schoterstask.presentation.detail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bakharaalief.schoterstask.domain.model.News
import com.bakharaalief.schoterstask.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class DetailViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {

    fun isNewsBookmarked(title: String) = newsUseCase.isNewsBookmarked(title)

    fun saveNews(news: News) {
        viewModelScope.launch {
            newsUseCase.saveNews(news)
        }
    }

    fun deleteNews(news: News) {
        viewModelScope.launch {
            newsUseCase.deleteNews(news)
        }
    }
}