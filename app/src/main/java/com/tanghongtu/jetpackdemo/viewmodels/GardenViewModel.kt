package com.tanghongtu.jetpackdemo.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.tanghongtu.jetpackdemo.data.GardenPlanting
import com.tanghongtu.jetpackdemo.data.GardenPlantingRepository
import com.tanghongtu.jetpackdemo.data.Plant

class GardenViewModel(
    private val gardenPlantingRepository: GardenPlantingRepository
) : ViewModel() {

    val gardenPlants = gardenPlantingRepository.getGardenPlantings()

}
