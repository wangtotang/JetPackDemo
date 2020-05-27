package com.tanghongtu.jetpackdemo.data

import androidx.room.TypeConverter
import java.util.*

/**
 * Created by tanghongtu on 2020/5/27.
 */
class Converters {

    @TypeConverter fun calendarToTimestamp(calendar: Calendar) = calendar.timeInMillis

    @TypeConverter fun timestampToCalendar(timestamp: Long) =
        Calendar.getInstance().apply { timeInMillis = timestamp }

}