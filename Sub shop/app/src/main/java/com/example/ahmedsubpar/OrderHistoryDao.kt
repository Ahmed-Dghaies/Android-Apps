package com.example.ahmedsubpar

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query

/**
 * data access object used to comunicate with the database
 */
@Dao
interface OrderHistoryDao{
    @Query("SELECT * FROM OrderHistory")
    fun getAll(): LiveData<List<OrderHistory>>

    @Insert
    fun insertAll(vararg orderHist: OrderHistory?)

    @Delete
    fun delete(orderHist: OrderHistory?)
}