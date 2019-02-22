package com.example.globe.ui.everything

import android.app.Activity
import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import com.example.globe.R
import kotlinx.android.synthetic.main.headline_fragment.*


class EverythingFragment : Fragment() {

    companion object {
        fun newInstance() = EverythingFragment()
    }

    private lateinit var viewModel: EverythingViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.everything_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(EverythingViewModel::class.java)

   //     toolbar.title = "Search"
    }

}
