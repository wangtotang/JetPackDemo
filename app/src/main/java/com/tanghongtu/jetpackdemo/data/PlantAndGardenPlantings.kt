package com.tanghongtu.jetpackdemo.data

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Relation

/**
 * Created by tanghongtu on 2020/5/28.
 */
data class PlantAndGardenPlantings(
    @Embedded val plant: Plant,

    @Relation(parentColumn = "id", entityColumn = "plant_id")
    val gardenPlantings: List<GardenPlanting>
)