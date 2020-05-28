package com.tanghongtu.jetpackdemo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tanghongtu.jetpackdemo.R
import com.tanghongtu.jetpackdemo.viewmodels.GardenViewModel

class GardenFragment : Fragment() {

    private lateinit var viewModel: GardenViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_garden, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(GardenViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
