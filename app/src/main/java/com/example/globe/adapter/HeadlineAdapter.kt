package com.example.globe.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globe.R
import com.example.globe.data.db.entity.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*

class HeadlineAdapter(val items: List<Article>, val context: Context) : RecyclerView.Adapter<ViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_news, parent, false))
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun getItemCount(): Int {
        return items.size
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        Picasso.get()
            .load(items[position].urlToImage)
            .resize(1000, 700)
            .centerCrop()
            .into(holder.img)

        holder.title.text = items[position].title
        holder.time.text = items[position].publishedAt
    }
}

class ViewHolder (view :View): RecyclerView.ViewHolder(view) {
    val img = view.item_news_image
    val title = view.item_title
    val time = view.item_time
}


