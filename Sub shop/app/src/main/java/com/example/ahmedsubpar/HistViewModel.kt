package com.example.ahmedsubpar

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData

/**
 * Connects repository to the views
 * @property repository repository used
 * @property history the list of the data recovered
 */
class HistViewModel(app: Application): AndroidViewModel(app) {

    private val repository: HistRepository = HistRepository(app)
    private val history: LiveData<List<OrderHistory>> = repository.getHistory()

    /**
     * all the repository method to get the order list
     */
    fun getHistory(): LiveData<List<OrderHistory>> {
        return history
    }

    /**
     * call the repository method to insert data in the database
     */
    fun insertHistory(hist: OrderHistory)
    {
        repository.insertHistory(hist)
    }
}