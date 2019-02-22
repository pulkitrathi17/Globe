package com.example.globe.data.db

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.globe.data.db.entity.Article

@Dao
interface NewsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE )
    fun upsertArticles(articles: List<Article>)

    @Query("SELECT * FROM article WHERE type = :type ORDER BY publishedAt DESC ")
    fun getArticles(type: String): LiveData<List<Article>>

    @Query("delete from article where type = :type")
    fun clearOldNews(type: String)


}