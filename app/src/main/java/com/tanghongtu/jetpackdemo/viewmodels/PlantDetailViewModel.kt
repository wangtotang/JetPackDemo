package com.tanghongtu.jetpackdemo.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.tanghongtu.jetpackdemo.data.GardenPlantingRepository
import com.tanghongtu.jetpackdemo.data.PlantRepository
import kotlinx.coroutines.launch

class PlantDetailViewModel internal constructor(
    private val plantRepository: PlantRepository,
    private val gardenPlantingRepository: GardenPlantingRepository,
    val plantId: String
) : ViewModel() {

    val plant = plantRepository.getPlant(plantId)

    val isPlanted = gardenPlantingRepository.isPlanted(plantId)

    fun addPlantToGarden() {
        viewModelScope.launch {
            gardenPlantingRepository.createGardenPlanting(plantId)
        }
    }

}
