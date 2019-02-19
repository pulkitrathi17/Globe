package com.example.globe.ui.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.globe.data.repository.NewsRepository
import com.example.globe.data.repository.NewsRepositoryImpl

class HeadlineViewModelFactory(
    private val newsRepositoryImpl: NewsRepositoryImpl
) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HeadlineViewModel(newsRepositoryImpl) as T
    }
}