package com.tanghongtu.jetpackdemo.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.observe
import androidx.viewpager2.widget.ViewPager2
import com.tanghongtu.jetpackdemo.R
import com.tanghongtu.jetpackdemo.adapters.GardenPlantListAdapter
import com.tanghongtu.jetpackdemo.adapters.PLANT_LIST_PAGER_INDEX
import com.tanghongtu.jetpackdemo.databinding.FragmentGardenBinding
import com.tanghongtu.jetpackdemo.utilities.InjectorUtils
import com.tanghongtu.jetpackdemo.viewmodels.GardenViewModel

class GardenFragment : Fragment() {

    private val gardenViewModel: GardenViewModel by viewModels {
        InjectorUtils.provideGardenViewModelFactory(requireActivity())
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentGardenBinding.inflate(inflater, container, false)
            .apply {

                val adapter = GardenPlantListAdapter()
                rlGardenList.adapter = adapter

                setClickListener {
                    navigateToPlant()
                }
                subscribeUi(adapter, this)
            }
        return binding.root
    }

    private fun navigateToPlant() {
        requireActivity().findViewById<ViewPager2>(R.id.vp_main).currentItem = PLANT_LIST_PAGER_INDEX
    }

    private fun subscribeUi(
        adapter: GardenPlantListAdapter,
        binding: FragmentGardenBinding
    ) {
        gardenViewModel.gardenPlants.observe(viewLifecycleOwner, {
            binding.hasPlantings = !it.isNullOrEmpty()
            adapter.submitList(it)
        })
    }

}
