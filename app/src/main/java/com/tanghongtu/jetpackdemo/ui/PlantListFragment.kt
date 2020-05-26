package com.tanghongtu.jetpackdemo.ui

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.tanghongtu.jetpackdemo.R
import com.tanghongtu.jetpackdemo.viewmodel.PlantListViewModel

class PlantListFragment : Fragment() {

    companion object {
        fun newInstance() = PlantListFragment()
    }

    private lateinit var listViewModel: PlantListViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.plant_list_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        listViewModel = ViewModelProviders.of(this).get(PlantListViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
