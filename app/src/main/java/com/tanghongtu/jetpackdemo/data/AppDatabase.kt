package com.tanghongtu.jetpackdemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import com.tanghongtu.jetpackdemo.utilities.DATABASE_NAME
import com.tanghongtu.jetpackdemo.workers.SeedDatabaseWorker

/**
 * Created by tanghongtu on 2020/5/27.
 */
@Database(entities = [Plant::class, GardenPlanting::class], version = 1, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
    abstract fun gardenPlantingDao(): GardenPlantingDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase {
            return instance ?: synchronized(this) {
                instance ?: buildDatabase(context).also { instance = it }
            }
        }

        private fun buildDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        val request = OneTimeWorkRequestBuilder<SeedDatabaseWorker>().build()
                        WorkManager.getInstance(context).enqueue(request)
                    }
                })
                .build()
        }
    }

}