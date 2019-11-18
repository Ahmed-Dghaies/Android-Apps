package com.example.ahmedsubpar

import android.app.Application
import android.os.AsyncTask
import androidx.lifecycle.LiveData

/**
 * Collections of all data in the app
 * @property orderHistoryDao the dao used to access the database
 * @property tData used to store our orders upon getting them from the database
 */
class HistRepository(app: Application) {
    private var orderHistoryDao: OrderHistoryDao
    var tData: LiveData<List<OrderHistory>>
    init {
        val database: HistoryDatabase = HistoryDatabase.getInstance(app)
        orderHistoryDao = database.orderHistoryDao()
        tData = orderHistoryDao.getAll()
    }

    /**
     * this method gets the list of the orders
     * @param hists list of order that we are going to set our data with
     */
    fun getHistory(): LiveData<List<OrderHistory>> {
        return tData
    }

    /**
     * this method set's the list of the orders
     * @param orderHistoryDao order that we are going to set our data with
     */
    fun insertHistory(orderHist: OrderHistory) {
        AsyncInsert(orderHistoryDao).execute(orderHist)
    }

    class AsyncInsert(val orderHistoryDao: OrderHistoryDao) : AsyncTask<OrderHistory, Void, Unit>() {
        /**
         * using to insert the data in the background
         */
        override fun doInBackground(
            vararg orderHist: OrderHistory?) {
            // * here unpacks the varargs so it can be
            // use with insertAll
            orderHistoryDao.insertAll(*orderHist)
        }
    }
}