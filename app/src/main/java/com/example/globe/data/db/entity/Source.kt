package com.example.globe.data.db.entity

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source (

    @SerializedName("id")
    var id: String? = null,

    @SerializedName("name")
    var name: String? = null

)
