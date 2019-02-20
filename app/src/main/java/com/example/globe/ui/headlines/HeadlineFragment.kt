package com.example.globe.ui.headlines

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer

import com.example.globe.R
import com.example.globe.ui.base.ScopedFragment
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

        buildUI()

    }

    private fun buildUI() = launch {
        var newsResponse = viewModel.news.await()
        newsResponse.observe(this@HeadlineFragment, Observer {
            if(it.isEmpty())
                return@Observer

            print(it[3].author)
        })
    }

}
