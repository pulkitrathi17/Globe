package com.example.globe.ui.headlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer

import com.example.globe.R
import com.example.globe.data.db.entity.Article
import com.example.globe.ui.base.ScopedFragment
import kotlinx.android.synthetic.main.headline_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance

class HeadlineFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory : HeadlineViewModelFactory by instance()

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


        (activity as? AppCompatActivity)?.supportActionBar?.title = "Headlines"
        (activity as? AppCompatActivity)?.supportActionBar?.subtitle = null

        buildUI()

    }

    private fun buildUI() = launch {
        val newsResponse = viewModel.topNews.await()
        newsResponse.observe(this@HeadlineFragment, Observer {
            if(it.isEmpty())
                return@Observer

            group_loading.visibility = View.GONE
        })
    }



}
