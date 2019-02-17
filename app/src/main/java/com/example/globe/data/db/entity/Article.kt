package com.example.globe.data.db.entity

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.globe.data.db.entity.Source
import com.google.gson.annotations.SerializedName

@Entity(tableName = "article")
class Article (

    @Embedded
    var source: Source? = null,
    @SerializedName("author")
    var author: String? = null,

    @PrimaryKey
    @SerializedName("title")
    var title: String? = null,
    @SerializedName("description")
    var description: String? = null,
    @SerializedName("url")
    var url: String? = null,
    @SerializedName("urlToImage")
    var urlToImage: String? = null,
    @SerializedName("publishedAt")
    var publishedAt: String? = null,
    @SerializedName("content")
    var content: String? = null

)
