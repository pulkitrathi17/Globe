package com.example.globe.data.db

import androidx.lifecycle.LiveData
import androidx.paging.DataSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.globe.data.db.entity.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun upsertArticles(articles: List<Article>)

    @Query("SELECT * FROM article ORDER BY publishedAt DESC ")
    fun getArticles(): LiveData<List<Article>>

    @Query("delete from article where type = :type")
    fun clearOldNews(type: String)

}