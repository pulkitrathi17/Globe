package com.example.globe.data.repository

import androidx.lifecycle.LiveData
import com.example.globe.data.db.entity.Article
import com.example.globe.data.db.entity.News

interface NewsRepository {
    suspend fun getNews(): LiveData<NewsRepository>
}