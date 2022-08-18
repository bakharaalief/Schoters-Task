package com.bakharaalief.schoterstask.presentation.main

import androidx.lifecycle.ViewModel
import com.bakharaalief.schoterstask.domain.usecase.NewsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(private val newsUseCase: NewsUseCase) : ViewModel() {
    fun getNews() = newsUseCase.getNews()
}