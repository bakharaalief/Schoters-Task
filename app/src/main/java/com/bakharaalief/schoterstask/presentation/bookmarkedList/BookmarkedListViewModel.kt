package com.bakharaalief.schoterstask.presentation.bookmarkedList

import androidx.lifecycle.ViewModel
import com.bakharaalief.schoterstask.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkedListViewModel @Inject constructor(private val newsUseCase: NewsUseCase) :
    ViewModel() {

    fun getBookmarkedNews() = newsUseCase.getBookmarkedNews()
}