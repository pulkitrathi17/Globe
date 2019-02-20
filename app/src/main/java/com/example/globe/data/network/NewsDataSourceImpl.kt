package com.example.globe.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.globe.data.network.response.NewsResponse
import com.example.globe.data.provider.SettingPreferences
import java.io.IOException

class NewsDataSourceImpl(private val api:Api) : NewsDataSource {

    private val _downloadedNews = MutableLiveData<NewsResponse>()

    override val downloadedNews: LiveData<NewsResponse>
        get() = _downloadedNews

    override suspend fun fetchTopNews() {
        try {
            val fetchedNews = api
                .fetchTopNews(SettingPreferences.getLocation())
                .await()
            _downloadedNews.postValue(fetchedNews)
        }
        catch (e: IOException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}