package com.example.globe.data.repository

import androidx.lifecycle.LiveData
import com.example.globe.data.db.entity.Article

interface NewsRepository {
    suspend fun getTopNews(): LiveData<List<Article>>
    suspend fun getEverything(query : String): LiveData<List<Article>>
}
