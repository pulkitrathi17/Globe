package com.example.globe.ui.headlines

import androidx.lifecycle.ViewModel
import com.example.globe.data.repository.NewsRepositoryImpl
import kotlinx.coroutines.*

class HeadlineViewModel(
    private val newsRepositoryImpl: NewsRepositoryImpl
) : ViewModel() {
    val news by lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            newsRepositoryImpl.getNews()
        }
    }
}
