package com.tanghongtu.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.tanghongtu.jetpackdemo.data.GardenPlantingRepository

/**
 * CREATE BY Tanghongtu 2020/5/30
 */
class GardenViewModelFactory(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModelProvider.Factory {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return GardenViewModel(gardenPlantingRepository) as T
    }

}