package com.example.globe.data.repository

import android.app.DownloadManager
import androidx.lifecycle.LiveData
import com.example.globe.data.db.NewsDao
import com.example.globe.data.db.entity.Article
import com.example.globe.data.db.entity.News
import com.example.globe.data.network.ConnectivityInterceptorImpl
import com.example.globe.data.network.NewsDataSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

class NewsRepositoryImpl(
    private val newsDao: NewsDao,
    private val newsDataSource: NewsDataSource
) : NewsRepository {

    init {
        newsDataSource.downloadedTopNews.observeForever { newTopNews ->
            persistFetchedTopNews(newTopNews)
        }
    }

    override suspend fun getEverything(query: String): LiveData<List<Article>> {
        return withContext(Dispatchers.IO) {
            fetchEverything(query)
            return@withContext newsDataSource.downloadedEverything
        }
    }

    override suspend fun getTopNews(): LiveData<List<Article>> {
        return withContext(Dispatchers.IO) {
            fetchTopNews()
            return@withContext newsDao.getArticles()
        }
    }


    private suspend fun fetchTopNews() {
        newsDataSource.fetchTopNews()
    }

    private suspend fun fetchEverything(query: String) {
        newsDataSource.fetchEverything(query)
    }

    private fun persistFetchedTopNews(articleList: List<Article>) {
        GlobalScope.launch(Dispatchers.IO) {

            //clear old db
            newsDao.clearOldNews()

            newsDao.upsertArticles(articleList)
        }
    }
}