package com.example.globe.data.network

import androidx.lifecycle.LiveData
import com.example.globe.data.db.entity.Article

interface NewsDataSource {
    val downloadedTopNews: LiveData<List<Article>>
    val downloadedEverything : LiveData<List<Article>>

    suspend fun fetchTopNews()
    suspend fun fetchEverything(query: String)

}