package com.tanghongtu.jetpackdemo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tanghongtu.jetpackdemo.R
import com.tanghongtu.jetpackdemo.viewmodel.PlantDetailViewModel

class PlantDetailFragment : Fragment() {

    companion object {
        fun newInstance() = PlantDetailFragment()
    }

    private lateinit var viewModel: PlantDetailViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plant_detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(PlantDetailViewModel::class.java)
        // TODO: Use the ViewModel
    }

}