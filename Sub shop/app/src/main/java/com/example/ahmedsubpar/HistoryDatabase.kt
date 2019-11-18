package com.example.ahmedsubpar

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.Room.*
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

/**
 * our database
 */
@Database(entities = [OrderHistory::class],version = 1, exportSchema = false)
@TypeConverters(DateConverter::class)
abstract class HistoryDatabase: RoomDatabase() {

    abstract fun orderHistoryDao(): OrderHistoryDao

    /**
     * used to get the instances
     */
    companion object {
        private var instance: HistoryDatabase? = null
        fun getInstance(context: Context): HistoryDatabase {
            if (instance != null) {
            } else {
                instance = databaseBuilder(
                    context,
                    HistoryDatabase::class.java, "conversion_db"
                )
                    // zap the database if changed//
                    //.fallbackToDestructiveMigration()
                    .build()
            }// cast it to the database type
            return instance as HistoryDatabase
        }
    }

}