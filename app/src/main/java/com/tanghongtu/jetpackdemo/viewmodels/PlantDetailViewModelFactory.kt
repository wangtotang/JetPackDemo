package com.tanghongtu.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanghongtu.jetpackdemo.data.GardenPlantingRepository
import com.tanghongtu.jetpackdemo.data.PlantRepository

/**
 * Created by tanghongtu on 2020/5/28.
 */
class PlantDetailViewModelFactory(
    private val plantRepository: PlantRepository,
    private val plantingRepository: GardenPlantingRepository,
    val plantId: String
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return PlantDetailViewModel(plantRepository, plantingRepository, plantId) as T
    }

}