package com.example.globe.data.repository

import androidx.lifecycle.LiveData
import com.example.globe.data.db.NewsDao
import com.example.globe.data.db.entity.Article
import com.example.globe.data.db.entity.News
import com.example.globe.data.network.NewsDataSource
import com.example.globe.data.network.NewsDataSourceImpl
import com.example.globe.data.network.response.NewsResponse
import com.example.globe.data.provider.SettingPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class NewsRepositoryImpl(
    private val newsDao: NewsDao,
    private val newsDataSource: NewsDataSource
) {

    init {
        newsDataSource.downloadedNews.observeForever { newNews ->
            persistFetchedNews(newNews)
        }
    }

    suspend fun getNews(): LiveData<List<Article>> {
        return withContext(Dispatchers.IO) {
            initNews()
            return@withContext newsDao.getArticles()
        }
    }

    private suspend fun initNews() {
        if (isFetchNewsNeeded()) {
            fetchNews()
        }
    }

    private suspend fun fetchNews() {
        newsDataSource.fetchTopNews()
    }

    private fun isFetchNewsNeeded(): Boolean {
        return true
    }

    private fun persistFetchedNews(newsResponse: NewsResponse) {
        GlobalScope.launch(Dispatchers.IO) {
            newsDao.upsertArticles(newsResponse.articles)
        }
    }
}