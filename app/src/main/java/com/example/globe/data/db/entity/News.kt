package com.example.globe.data.db.entity

import com.google.gson.annotations.SerializedName

data class News(

    @SerializedName("status")
    var status: String? = "",

    @SerializedName("totalResults")
    var totalResults: Int? = -1,

    @SerializedName("articles")
    var articles: List<Article> = emptyList()

)
