package com.tanghongtu.jetpackdemo.viewmodels

import androidx.lifecycle.*
import com.tanghongtu.jetpackdemo.data.Plant
import com.tanghongtu.jetpackdemo.data.PlantRepository

class PlantListViewModel internal constructor(
    plantRepository: PlantRepository,
    private val handle: SavedStateHandle
) : ViewModel() {

    val plants : LiveData<List<Plant>> = getSavedGrowZoneNumber().switchMap {
        when (it) {
            NO_GROW_ZONE -> plantRepository.getPlants()
            else -> plantRepository.getPlantsWithGrowZoneNumber(it)
        }
    }

    fun setGrowZoneNumber(num: Int) {
        handle.set(GROW_ZONE_SAVED_STATE_KEY, num)
    }

    fun clearGrowZoneNumber() {
        handle.set(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE)
    }

    fun isFiltered() = getSavedGrowZoneNumber().value != NO_GROW_ZONE

    private fun getSavedGrowZoneNumber(): MutableLiveData<Int> {
        return handle.getLiveData(GROW_ZONE_SAVED_STATE_KEY, NO_GROW_ZONE)
    }

    companion object {
        private const val GROW_ZONE_SAVED_STATE_KEY = "GROW_ZONE_SAVED_STATE_KEY"
        private const val NO_GROW_ZONE = -1
    }

}
