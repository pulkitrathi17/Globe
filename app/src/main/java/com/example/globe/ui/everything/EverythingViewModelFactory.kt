package com.example.globe.ui.headlines

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.globe.data.repository.NewsRepository

class EverythingViewModelFactory(
    private val newsRepository: NewsRepository

) : ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return EverythingViewModel(newsRepository) as T
    }
}