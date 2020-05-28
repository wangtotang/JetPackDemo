package com.tanghongtu.jetpackdemo.viewmodels

import android.os.Bundle
import androidx.lifecycle.AbstractSavedStateViewModelFactory
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.savedstate.SavedStateRegistryOwner
import com.tanghongtu.jetpackdemo.data.PlantRepository

/**
 * Created by tanghongtu on 2020/5/27.
 */
class PlantListViewModelFactory(
    private val plantRepository: PlantRepository,
    owner: SavedStateRegistryOwner,
    defaultArgs: Bundle? = null
) : AbstractSavedStateViewModelFactory(owner, defaultArgs) {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel?> create(
        key: String,
        modelClass: Class<T>,
        handle: SavedStateHandle
    ): T {
        return PlantListViewModel(plantRepository, handle) as T
    }

}