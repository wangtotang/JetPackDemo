package com.tanghongtu.jetpackdemo.data

/**
 * Created by tanghongtu on 2020/5/27.
 */
class PlantRepository private constructor(private val plantDao: PlantDao) {

    fun getPlants() = plantDao.getPlants()

    fun getPlant(plantId: String) = plantDao.getPlant(plantId)

    fun getPlantsWithGrowZoneNumber(growZoneNumber: Int) =
        plantDao.getPlantsWithGrowZoneNumber(growZoneNumber)

    companion object {

        @Volatile private var instance: PlantRepository? = null

        fun getInstance(plantDao: PlantDao): PlantRepository {
            return instance ?: synchronized(this){
                instance ?: PlantRepository(plantDao).also { instance = it }
            }
        }
    }

}