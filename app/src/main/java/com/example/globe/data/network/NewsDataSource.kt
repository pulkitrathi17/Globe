package com.example.globe.data.network

import androidx.lifecycle.LiveData
import com.example.globe.data.network.response.NewsResponse

interface NewsDataSource {
    val downloadedNews: LiveData<NewsResponse>

    suspend fun fetchTopNews(
        country: String
    )
}