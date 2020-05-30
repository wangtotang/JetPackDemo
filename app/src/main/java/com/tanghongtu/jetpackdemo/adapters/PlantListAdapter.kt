package com.tanghongtu.jetpackdemo.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.tanghongtu.jetpackdemo.data.Plant
import com.tanghongtu.jetpackdemo.databinding.ItemPlantListBinding
import com.tanghongtu.jetpackdemo.ui.MainViewPagerFragmentDirections

/**
 * Created by tanghongtu on 2020/5/27.
 */
class PlantListAdapter : ListAdapter<Plant, PlantListAdapter.PlantListViewHolder>(
    object : DiffUtil.ItemCallback<Plant>(){
        override fun areItemsTheSame(oldItem: Plant, newItem: Plant) =
            oldItem.plantId == newItem.plantId

        override fun areContentsTheSame(oldItem: Plant, newItem: Plant) =
            oldItem == newItem

    }
) {

    class PlantListViewHolder(
        private val binding: ItemPlantListBinding
    ) : RecyclerView.ViewHolder(binding.root) {

        init {
            binding.setClickListener {
                binding.plant?.let { plant ->
                    navigateToPlantDetail(plant, it)
                }
            }
        }

        private fun navigateToPlantDetail(plant: Plant, view: View) {
            val direction =
                MainViewPagerFragmentDirections.actionMainViewPagerFragmentToPlantDetailFragment(
                    plant.plantId
                )
            view.findNavController().navigate(direction)
        }

        fun bind(item: Plant) {
            with(binding) {
                plant = item
                executePendingBindings()
            }
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) =
        PlantListViewHolder(ItemPlantListBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: PlantListViewHolder, position: Int) {
        holder.bind(getItem(position))
    }



}