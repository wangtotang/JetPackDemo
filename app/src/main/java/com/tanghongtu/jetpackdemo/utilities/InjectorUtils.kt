package com.tanghongtu.jetpackdemo.utilities

import android.content.Context
import androidx.fragment.app.Fragment
import com.tanghongtu.jetpackdemo.data.AppDatabase
import com.tanghongtu.jetpackdemo.data.GardenPlantingRepository
import com.tanghongtu.jetpackdemo.data.PlantRepository
import com.tanghongtu.jetpackdemo.viewmodels.PlantDetailViewModelFactory
import com.tanghongtu.jetpackdemo.viewmodels.PlantListViewModelFactory

/**
 * Created by tanghongtu on 2020/5/27.
 */
object InjectorUtils {

    private fun getPlantRepository(context: Context) =
        PlantRepository.getInstance(AppDatabase.getInstance(context.applicationContext).plantDao())

    fun providePlantListViewModelFactory(fragment: Fragment) =
        PlantListViewModelFactory(getPlantRepository(fragment.requireContext()), fragment)

    private fun getGardenRepository(context: Context): GardenPlantingRepository {
        return GardenPlantingRepository.getInstance(
            AppDatabase.getInstance(context.applicationContext).gardenPlantingDao()
        )
    }

    fun providePlantDetailViewModelFactory(context: Context, plantId: String): PlantDetailViewModelFactory {
        return PlantDetailViewModelFactory(
            getPlantRepository(context), getGardenRepository(context), plantId
        )
    }

}