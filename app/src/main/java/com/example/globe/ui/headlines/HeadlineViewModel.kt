package com.example.globe.ui.headlines

import androidx.lifecycle.ViewModel
import com.example.globe.data.repository.NewsRepository
import kotlinx.coroutines.*

class HeadlineViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {
    val topNews by lazy {
        GlobalScope.async(start = CoroutineStart.LAZY) {
            newsRepository.getTopNews()
        }
    }
}
