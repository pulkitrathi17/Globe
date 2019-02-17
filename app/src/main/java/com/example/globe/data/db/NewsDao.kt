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

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun upsertArticles(articles: List<Article>)

/*    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertSources(sources: List<SourceModel>)*/

/*    @Query("SELECT COUNT(*) FROM sourcemodel")
    fun getSourcesCount():Int*/

    @Query("SELECT * FROM article ORDER BY publishedAt DESC")
    fun getArticles(): LiveData<Article>
 //   fun getAllArticles(): DataSource.Factory<Int, Article>

/*    @Query("SELECT * FROM sourcemodel ORDER BY name ASC")
    fun getAllSources(): LiveData<List<SourceModel>>*/

    @Query("SELECT * FROM article ORDER BY publishedAt DESC LIMIT 1")
    fun getTopArticle(): Article
}