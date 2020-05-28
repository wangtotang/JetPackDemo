package com.tanghongtu.jetpackdemo.data

/**
 * Created by tanghongtu on 2020/5/28.
 */
class GardenPlantingRepository private constructor(
    private val gardenPlantingDao: GardenPlantingDao
) {

    fun getGardenPlantings() = gardenPlantingDao.getGardenPlantings()

    suspend fun createGardenPlanting(plantId: String) {
        val gardenPlanting = GardenPlanting(plantId)
        gardenPlantingDao.insertGardenPlanting(gardenPlanting)
    }

    suspend fun removeGardenPlanting(gardenPlanting: GardenPlanting) {
        gardenPlantingDao.deleteGardenPlanting(gardenPlanting)
    }

    fun isPlanted(plantId: String) = gardenPlantingDao.isPlanted(plantId)

    companion object {

        @Volatile private var instance: GardenPlantingRepository? = null

        fun getInstance(gardenPlantingDao: GardenPlantingDao) =
            instance ?: synchronized(this) {
                instance ?: GardenPlantingRepository(gardenPlantingDao).also { instance = it }
            }


    }

}