package com.tanghongtu.jetpackdemo.data

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * Created by tanghongtu on 2020/5/26.
 */
@Entity(tableName = "plants")
data class Plant(

    @PrimaryKey
    @ColumnInfo(name = "id")
    val plantId: String,
    //使用val声明的默认为NOT NULL
    val name: String,

    val description: String,

    @ColumnInfo(name = "grow_zone_number")
    val growZoneNumber: Int,

    val wateringInterval: Int = 7,
    val imageUrl: String = ""
){

    override fun toString() = name

    fun shouldBeWatered(since: Calendar, lastWateringDate: Calendar) =
        since > lastWateringDate.apply { add(Calendar.DAY_OF_YEAR, wateringInterval) }

}