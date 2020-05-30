package com.tanghongtu.jetpackdemo.viewmodels

import com.tanghongtu.jetpackdemo.data.PlantAndGardenPlantings
import java.text.SimpleDateFormat

/**
 * CREATE BY Tanghongtu 2020/5/30
 */
class PlantAndGardenPlantingsViewModel(plantings: PlantAndGardenPlantings) {

    private val plant = checkNotNull(plantings.plant)
    private val gardenPlanting = plantings.gardenPlantings[0]

    val waterDateString: String = dateFormat.format(gardenPlanting.lastWateringDate.time)

    val wateringInterval
        get() = plant.wateringInterval

    val imageUrl
        get() = plant.imageUrl

    val plantName
        get() = plant.name

    val plantDateString: String = dateFormat.format(gardenPlanting.plantDate.time)

    val plantId
        get() = plant.plantId

    companion object {
        private val dateFormat = SimpleDateFormat("YYYY-MM-dd")
    }

}