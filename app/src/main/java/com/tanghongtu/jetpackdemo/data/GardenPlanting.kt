package com.tanghongtu.jetpackdemo.data

import androidx.room.*
import java.util.*

/**
 * Created by tanghongtu on 2020/5/28.
 */
@Entity(
    tableName = "garden_plantings",
    foreignKeys = [
        ForeignKey(entity = Plant::class, parentColumns = ["id"], childColumns = ["plant_id"])
    ],
    indices = [Index("plant_id")]
)
data class GardenPlanting(

    @ColumnInfo(name = "plant_id")
    val plantId: String,

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id")
    val gardenPlantingId: Long = 0,

    val plantDate: Calendar = Calendar.getInstance(),
    val lastWateringDate: Calendar = Calendar.getInstance()
)