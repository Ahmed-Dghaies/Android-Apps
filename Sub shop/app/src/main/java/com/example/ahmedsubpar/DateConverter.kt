package com.example.ahmedsubpar

import androidx.room.TypeConverter
import java.util.*

/**
 * This class converts the dates to long and back to date
 */
class DateConverter {
    /**
     * convert date to long
     * @param date takes a date as a paranmeter
     */
    @TypeConverter
    fun dateToLong(date: Date?): Long?
    {
        return date?.time
    }

    /**
     * convert long to date
     * @param timestamp takes a long as a paranmeter
     */
    @TypeConverter
    fun longToDate(timestamp: Long?) : Date?
    {
        return timestamp?.let { Date(it) }
    }
}