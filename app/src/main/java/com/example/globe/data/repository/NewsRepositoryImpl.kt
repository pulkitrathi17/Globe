package com.example.globe.data.repository

import androidx.lifecycle.LiveData
import com.example.globe.data.db.NewsDao
import com.example.globe.data.db.entity.Article
import com.example.globe.data.db.entity.News
import com.example.globe.data.network.ConnectivityInterceptorImpl
import com.example.globe.data.network.NewsDataSource
import com.example.globe.data.network.NewsDataSourceImpl
import com.example.globe.data.network.response.NewsResponse
import com.example.globe.data.provider.SettingPreferences
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.time.ZonedDateTime

const val TOPNEWS = "topnews"
const val EVERYTHING = "everything"

class NewsRepositoryImpl(
    private val newsDao: NewsDao,
    private val newsDataSource: NewsDataSource
):NewsRepository {

    init {
        newsDataSource.downloadedTopNews.observeForever { newTopNews ->
            persistFetchedTopNews(newTopNews)
        }
    }

    override suspend fun getEverything(): LiveData<List<Article>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override suspend fun getTopNews(): LiveData<List<Article>> {
        return withContext(Dispatchers.IO) {
            initNews()
            return@withContext newsDao.getArticles()
        }
    }

    private suspend fun initNews() {
        if (isFetchNewsNeeded()) {
            fetchTopNews()
        }
    }

    private suspend fun fetchTopNews() {
        newsDataSource.fetchTopNews()
    }

    private fun isFetchNewsNeeded(): Boolean {
        return true
    }

    private fun persistFetchedTopNews(newsResponse: NewsResponse) {
        GlobalScope.launch(Dispatchers.IO) {

            //clear old db
            newsDao.clearOldNews(TOPNEWS)

            //to add type of request
            newsResponse.articles.forEach { it-> it.type= TOPNEWS }
            newsDao.upsertArticles(newsResponse.articles)
        }
    }
}