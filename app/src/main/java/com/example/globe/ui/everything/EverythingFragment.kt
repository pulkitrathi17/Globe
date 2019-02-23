package com.example.globe.ui.everything


import android.content.Intent
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.Toast
import android.widget.Toast.LENGTH_LONG
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.globe.R
import com.example.globe.data.db.entity.Article
import com.example.globe.ui.WebViewActivity
import com.example.globe.ui.base.ScopedFragment
import com.example.globe.ui.headlines.EverythingViewModel
import com.example.globe.ui.headlines.EverythingViewModelFactory
import kotlinx.android.synthetic.main.everything_fragment.*
import kotlinx.coroutines.launch
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein
import org.kodein.di.generic.instance


class EverythingFragment : ScopedFragment(), KodeinAware {
    override val kodein by closestKodein()
    private val viewModelFactory: EverythingViewModelFactory by instance()

    private lateinit var viewModel: EverythingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(EverythingViewModel::class.java)
        initUI()
    }

    private fun initUI(){

    }

    private fun buildUI(query: String) = launch {
        val newsResponse = viewModel.getEverythingNews(query).await()
        newsResponse.observe(this@EverythingFragment, Observer {

            if (it.isEmpty()) {
                Toast.makeText(this@EverythingFragment.context, "No results found!!", LENGTH_LONG).show()
            }
            group_loading.visibility = View.GONE
            initRecyclerView(it)
        })
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        search_button_back.setOnClickListener {
            findNavController().popBackStack()
        }

        search_button_clear.setOnClickListener {
            search_input_query.text = null
        }


        search_input_query.setOnEditorActionListener { textView, i, keyEvent ->
            if (i == EditorInfo.IME_ACTION_DONE) {

                if (textView.text.isNotEmpty()) {
                    group_loading.visibility = View.VISIBLE
                    textView.text?.let { buildUI(textView.text.toString()) }
                }

                true
            }
            false
        }

        search_input_query.text = null

    }


    private fun initRecyclerView(items: List<Article>) {

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@EverythingFragment.context)
            adapter = EverythingRecyclerAdapter(items) { article: Article -> newsItemClicked(article) }
        }

    }

    private fun newsItemClicked(article: Article) {
        val intent = Intent(this@EverythingFragment.context, WebViewActivity::class.java)
        val url: String? = article.url
        intent.putExtra("URL", url)
        startActivity(intent)
    }

    override fun onResume() {
        super.onResume()
        (activity as AppCompatActivity).supportActionBar?.hide()
    }

    override fun onStop() {
        super.onStop()
        (activity as AppCompatActivity).supportActionBar?.show()
    }

}
