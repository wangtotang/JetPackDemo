package com.tanghongtu.jetpackdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import com.tanghongtu.jetpackdemo.adapters.PlantListAdapter
import com.tanghongtu.jetpackdemo.databinding.PlantListFragmentBinding
import com.tanghongtu.jetpackdemo.utilities.InjectorUtils
import com.tanghongtu.jetpackdemo.viewmodels.PlantListViewModel

class PlantListFragment : Fragment() {

    private val viewModel: PlantListViewModel by viewModels {
        InjectorUtils.providePlantListViewModelFactory(this)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = PlantListFragmentBinding.inflate(inflater, container, false)

        val adapter = PlantListAdapter()
        binding.rlPlantList.adapter = adapter
        subscribeUi(adapter)

        return binding.root
    }

    private fun subscribeUi(adapter: PlantListAdapter) {
        viewModel.plants.observe(viewLifecycleOwner) { plants ->
            adapter.submitList(plants)
        }
    }

}
