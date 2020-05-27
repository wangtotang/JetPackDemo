package com.tanghongtu.jetpackdemo.workers

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.google.gson.stream.JsonReader
import com.tanghongtu.jetpackdemo.data.AppDatabase
import com.tanghongtu.jetpackdemo.data.Plant
import com.tanghongtu.jetpackdemo.utilities.PLANT_DATA_FILENAME
import kotlinx.coroutines.coroutineScope
import java.io.IOException

/**
 * Created by tanghongtu on 2020/5/27.
 */
class SeedDatabaseWorker(
    appContext: Context,
    params: WorkerParameters
) : CoroutineWorker(appContext, params) {

    override suspend fun doWork() = coroutineScope {

        try {
            applicationContext.assets.open(PLANT_DATA_FILENAME).use { inputStream ->
                JsonReader(inputStream.reader()).use { jsonReader ->
                    val plantType = object : TypeToken<List<Plant>>() {}.type
                    val plantList = Gson().fromJson<List<Plant>>(jsonReader, plantType)

                    AppDatabase.getInstance(applicationContext).plantDao().insertAll(plantList)

                    Result.success()
                }
            }
        } catch (e: Exception) {
            Log.e(TAG, e.message)
            Result.failure()
        }

    }

    companion object {
        private const val TAG = "SeedDatabaseWorker"
    }



}