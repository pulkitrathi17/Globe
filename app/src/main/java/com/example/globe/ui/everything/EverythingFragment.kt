package com.example.globe.ui.everything

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.globe.R

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
        // TODO: Use the ViewModel
    }

}
