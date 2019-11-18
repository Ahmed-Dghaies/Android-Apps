package com.example.ahmedsubpar

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import java.util.*


/**
 * this adapter takes the data and put it into the view
 * @property orderHistoryList a list of all orders
 */
class HistAdapter() : RecyclerView.Adapter<HistAdapter.ViewHolder>() {

    private var orderHistoryList: List<OrderHistory>? = null

    class HistHolder(val histView: View)  : RecyclerView.ViewHolder(histView)


    /**
     * this method is returning the view for each item in the list
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistAdapter.ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.list_layout, parent, false)
        return ViewHolder(v)
    }

    /**
     * this method is binding the data on the list
     * @param holder the adapter holder
     * @param position the position of the item in the list
     */
    override fun onBindViewHolder(holder: HistAdapter.ViewHolder, position: Int) {
        val order_date = holder.itemView.findViewById<TextView>(R.id.order_date)
        val order_price = holder.itemView.findViewById<TextView>(R.id.order_price)
        val order_title = holder.itemView.findViewById<TextView>(R.id.sandwich_title)

        if (orderHistoryList != null) {
            val hist = orderHistoryList?.get(position)?: OrderHistory(null, "","",0.0f, Date())
            order_date.text = hist.orderDate.toString()
            order_price.text = hist.price.toString()
            order_title.text = hist.sandwichType + " " + hist.breadType
        } else {
            order_date.text = ""
            order_price.text = ""
            order_title.text = ""
        }
    }


    /**
     * this method is giving the size of the list
     */
    override fun getItemCount(): Int {
        return this.orderHistoryList?.size ?: 0
    }

    /**
     * this method set's the list of the orders
     * @param hists list of order that we are going to set our data with
     */
    fun setHistory (hists: List<OrderHistory>) {
        // not the best way to do this
        orderHistoryList = hists
        // invalidates all, pretty heavy handed
        // there are better ways
        notifyDataSetChanged()
    }

    /**
     * the class is hodling the list view
     * @param itemView an item of our list
     */
    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindItems(orderHistory: OrderHistory) {
            val textViewDate = itemView.findViewById(R.id.order_date) as TextView
            val textViewprice  = itemView.findViewById(R.id.order_price) as TextView
            val textViewTitle = itemView.findViewById(R.id.sandwich_title) as TextView
            textViewDate.text = orderHistory.orderDate.toString()
            textViewprice.text = orderHistory.price.toString()
            textViewTitle.text = orderHistory.sandwichType + " " + orderHistory.breadType
        }
    }
}