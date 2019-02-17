package com.example.globe.data.network.response

import com.example.globe.data.db.entity.Article

data class NewsResponse (
    var status: String,
    var totalResults: String,
    var articles: List<Article>
)