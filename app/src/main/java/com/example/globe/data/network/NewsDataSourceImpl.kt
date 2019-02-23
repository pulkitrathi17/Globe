package com.example.globe.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.room.Query
import com.example.globe.data.db.entity.Article
import com.example.globe.data.network.response.NewsResponse
import com.example.globe.data.provider.SettingPreferences
import java.io.IOException

class NewsDataSourceImpl(private val api: Api) : NewsDataSource {

    private val _downloadedTopNews = MutableLiveData<List<Article>>()
    private val _downloadedEverything = MutableLiveData<List<Article>>()


    override val downloadedTopNews: LiveData<List<Article>>
        get() = _downloadedTopNews

    override val downloadedEverything: LiveData<List<Article>>
        get() = _downloadedEverything

    override suspend fun fetchTopNews() {
        try {
            val fetchedNews = api
                .fetchTopNews(SettingPreferences.getLocation())
                .await()
            _downloadedTopNews.postValue(fetchedNews.articles)
        } catch (e: IOException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }


    override suspend fun fetchEverything(query: String) {
        try {
            val fetchedNews = api
                .fetchEverything(query, SettingPreferences.getLanguage())
                .await()
            _downloadedEverything.postValue(fetchedNews.articles)
        } catch (e: IOException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}