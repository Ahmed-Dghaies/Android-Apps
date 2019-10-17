package com.example.ahmedsubpar

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

/**
 * this is a data class for the order details
 * @property sandwich_type the type of the sandwish
 * @property bread_type the type of the used bread
 * @property toppings the toppings list
 * @property num_toppings the number of toppings
 * @property total_price the total price of the sandwish
 */
@Parcelize
data class OrderDetails (
    var sandwich_type: String = "",
    var bread_type: String = "",
    var toppings: String = "",
    var num_toppings: Int = 0,
    var total_price: Float = 0.0f
) : Parcelable