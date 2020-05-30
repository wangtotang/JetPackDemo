package com.tanghongtu.jetpackdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanghongtu.jetpackdemo.data.PlantAndGardenPlantings
import com.tanghongtu.jetpackdemo.databinding.ItemGardenPlantingBinding
import com.tanghongtu.jetpackdemo.ui.MainViewPagerFragmentDirections
import com.tanghongtu.jetpackdemo.viewmodels.PlantAndGardenPlantingsViewModel

/**
 * CREATE BY Tanghongtu 2020/5/30
 */
class GardenPlantListAdapter : ListAdapter<PlantAndGardenPlantings, GardenPlantListAdapter.GardenListViewHolder>(
    object : DiffUtil.ItemCallback<PlantAndGardenPlantings>() {
        override fun areItemsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
            return oldItem.plant.plantId == newItem.plant.plantId
        }

        override fun areContentsTheSame(oldItem: PlantAndGardenPlantings, newItem: PlantAndGardenPlantings): Boolean {
            return oldItem == newItem
        }
    }
) {

    class GardenListViewHolder(
        private val binding: ItemGardenPlantingBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.viewModel?.plantId?.let { plantId ->
                    navigateToPlantDetail(plantId, it)
                }
            }
        }

        private fun navigateToPlantDetail(plantId: String, view: View) {
            val direction = MainViewPagerFragmentDirections
                .actionMainViewPagerFragmentToPlantDetailFragment(plantId)
            view.findNavController().navigate(direction)
        }

        fun bind(item: PlantAndGardenPlantings) {
            with(binding) {
                viewModel = PlantAndGardenPlantingsViewModel(item)
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GardenListViewHolder {
        return GardenListViewHolder(
            ItemGardenPlantingBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: GardenListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

}