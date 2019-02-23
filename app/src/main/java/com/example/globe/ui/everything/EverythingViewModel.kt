package com.example.globe.ui.headlines

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.globe.data.db.entity.Article
import com.example.globe.data.repository.NewsRepository
import kotlinx.coroutines.*

class EverythingViewModel(
    private val newsRepository: NewsRepository
) : ViewModel() {

    fun getEverythingNews(query: String): Deferred<LiveData<List<Article>>> {
        return GlobalScope.async(start = CoroutineStart.LAZY) {
            newsRepository.getEverything(query)
        }
    }

}
