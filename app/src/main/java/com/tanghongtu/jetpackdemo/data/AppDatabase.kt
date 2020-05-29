package com.tanghongtu.jetpackdemo.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import androidx.work.OneTimeWorkRequestBuilder
import androidx.work.WorkManager
import androidx.work.Worker
import com.tanghongtu.jetpackdemo.utilities.DATABASE_NAME
import com.tanghongtu.jetpackdemo.workers.SeedDatabaseWorker

/**
 * Created by tanghongtu on 2020/5/27.
 */
@Database(entities = [Plant::class, GardenPlanting::class], version = 3, exportSchema = false)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {

    abstract fun plantDao(): PlantDao
    abstract fun gardenPlantingDao(): GardenPlantingDao

    companion object {

        @Volatile private var instance: AppDatabase? = null

        private val MIGRATION_1_2 = object : Migration(1, 2) {

            override fun migrate(database: SupportSQLiteDatabase) {
                //创建表
                database.execSQL(
                    "CREATE TABLE IF NOT EXISTS garden_plantings (plant_id TEXT, id LONG PRIMARY KEY AUTOINCREMENT NOT NULL, plantDate LONG, lastWateringDate LONG, FOREIGN KEY(plant_id) REFERENCES plants(id))"
                )
                //创建普通索引
                database.execSQL("CREATE INDEX IF NOT EXISTS index_garden_plantings_plant_id ON garden_plantings (plant_id)")

            }

        }

        private val MIGRATION_2_3 = object : Migration(2, 3) {

            override fun migrate(database: SupportSQLiteDatabase) {
                //1.创建表
                database.execSQL("CREATE TABLE plants_new (id TEXT PRIMARY KEY NOT NULL, name TEXT NOT NULL, description TEXT NOT NULL, grow_zone_number INTEGER NOT NULL, wateringInterval INTEGER NOT NULL, imageUrl TEXT NOT NULL)")

                //2.拷贝表
                database.execSQL("INSERT INTO plants_new (id, name, description, grow_zone_number, wateringInterval, imageUrl) SELECT id, name, description, growZoneNumber, wateringInterval, imageUrl FROM plants")

                //3.删除旧表
                database.execSQL("DROP TABLE plants")

                //4.重命名新表
                database.execSQL("ALTER TABLE plants_new RENAME TO plants")
            }

        }

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
                .addMigrations(MIGRATION_1_2, MIGRATION_2_3)
                .build()
        }
    }

}