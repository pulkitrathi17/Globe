package com.example.globe.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.globe.data.network.response.NewsResponse
import com.example.globe.data.provider.SettingPreferences
import java.io.IOException

class NewsDataSourceImpl(private val api: Api) : NewsDataSource {

    private val _downloadedTopNews = MutableLiveData<NewsResponse>()

    override val downloadedTopNews: LiveData<NewsResponse>
        get() = _downloadedTopNews

    override suspend fun fetchTopNews() {
        try {
            val fetchedNews = api
                .fetchTopNews(SettingPreferences.getLocation())
                .await()
            _downloadedTopNews.postValue(fetchedNews)
        } catch (e: IOException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}