package com.tanghongtu.jetpackdemo.data

import androidx.lifecycle.LiveData
import androidx.room.*

/**
 * Created by tanghongtu on 2020/5/28.
 */
@Dao
interface GardenPlantingDao {

    @Query("SELECT * FROM garden_plantings")
    fun getGardenPlantings(): LiveData<List<GardenPlanting>>

    @Query("SELECT EXISTS(SELECT 1 FROM garden_plantings where plant_id = :plantId LIMIT 1)")
    fun isPlanted(plantId: String): LiveData<Boolean>

    @Insert
    suspend fun insertGardenPlanting(gardenPlanting: GardenPlanting): Long

    @Delete
    suspend fun deleteGardenPlanting(gardenPlanting: GardenPlanting)

    @Transaction
    @Query("SELECT * FROM plants WHERE id IN (SELECT DISTINCT(plant_id) FROM garden_plantings)")
    fun getPlantedGardens(): LiveData<List<PlantAndGardenPlantings>>

}