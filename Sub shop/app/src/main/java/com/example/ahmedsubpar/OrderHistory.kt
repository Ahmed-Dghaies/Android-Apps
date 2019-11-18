package com.example.ahmedsubpar

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

/**
 * the entity used to save the orders in the database
 */
@Entity
data class OrderHistory(
    @PrimaryKey val orderId: Long?,
    @ColumnInfo(name = "sandwich_type")
    val sandwichType: String,
    @ColumnInfo(name = "bread_type")
    val breadType: String,
    @ColumnInfo(name = "price")
    val price: Float,
    @ColumnInfo(name = "date")
    val orderDate: Date
)