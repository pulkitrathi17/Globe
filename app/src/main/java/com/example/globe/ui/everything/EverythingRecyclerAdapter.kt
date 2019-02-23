package com.example.globe.ui.everything

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.globe.R
import com.example.globe.data.db.entity.Article
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_news.view.*


class EverythingRecyclerAdapter(private val newsList: List<Article>, private val clickListener: (Article) -> Unit) :
    RecyclerView.Adapter<NewsViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NewsViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.item_news, parent, false)
        return NewsViewHolder(v)
    }

    override fun onBindViewHolder(holder: NewsViewHolder, position: Int) {
        holder.bindItems(newsList[position], clickListener)
    }

    // Gets the number of articles in the list
    override fun getItemCount(): Int {
        return newsList.size
    }

}

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bindItems(article: Article, clickListener: (Article) -> Unit) {

        itemView.item_title.text = article.title

        Picasso.get()
            .load(article.urlToImage ?: "https://1080motion.com/wp-content/uploads/2018/06/NoImageFound.jpg.png")
            .fit()
            .placeholder(R.drawable.jougan)
            .into(itemView.item_news_image)

        itemView.item_time.text = article.publishedAt?.subSequence(0,10)

        itemView.parentView.setOnClickListener {
            clickListener(article)
        }
    }

}