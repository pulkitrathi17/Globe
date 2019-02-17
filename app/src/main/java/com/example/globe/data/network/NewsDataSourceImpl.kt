package com.example.globe.data.network

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.globe.data.network.response.NewsResponse
import com.example.globe.internal.NoConnectivityException

class NewsDataSourceImpl(private val api:Api) : NewsDataSource {

    private val _downloadedNews = MutableLiveData<NewsResponse>()

    override val downloadedNews: LiveData<NewsResponse>
        get() = _downloadedNews

    override suspend fun fetchTopNews(country: String) {
        try {
            val fetchedNews = api
                .fetchTopNews(country)
                .await()
            _downloadedNews.postValue(fetchedNews)
        }
        catch (e: NoConnectivityException) {
            Log.e("Connectivity", "No internet connection.", e)
        }
    }
}