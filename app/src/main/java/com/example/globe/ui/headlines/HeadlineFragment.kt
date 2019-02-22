package com.example.globe.ui.headlines

import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globe.GlobeApplication

import com.example.globe.R
import com.example.globe.data.db.entity.Article
import com.example.globe.ui.WebViewActivity
import com.example.globe.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.headline_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HeadlineFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: HeadlineViewModelFactory by instance()

    private lateinit var viewModel: HeadlineViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.headline_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory)
            .get(HeadlineViewModel::class.java)

       toolbar.title = "Headline"

        buildUI()

    }

    private fun buildUI() = launch {
        val newsResponse = viewModel.topNews.await()
        newsResponse.observe(this@HeadlineFragment, Observer {
            if (it.isEmpty())
                return@Observer

            group_loading.visibility = View.GONE
            initRecyclerView(it)
        })
    }

    private fun initRecyclerView(items: List<Article>) {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@HeadlineFragment.context)
            adapter = HeadlineRecyclerAdapter(items) { article: Article -> newsItemClicked(article) }
        }

    }

    private fun newsItemClicked(article: Article) {
        val intent = Intent(this@HeadlineFragment.context, WebViewActivity::class.java)
        val url: String? = article.url
        intent.putExtra("URL", url)
        startActivity(intent)
    }

}
