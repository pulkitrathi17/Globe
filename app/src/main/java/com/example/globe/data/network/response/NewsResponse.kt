package com.example.globe.data.network.response

import com.example.globe.data.db.entity.Article
import com.example.globe.data.db.entity.News

data class NewsResponse (
    var status: String,
    var totalResults: Int,
    var articles: List<Article>

)