package com.example.ahmedsubpar

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.ahmedsubpar.databinding.FragmentHistoryBinding
import kotlin.collections.ArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.navigation.findNavController

/**
 * fragnebt used to show the previous sandwich details
 * @property binding used to bind views of the layout
 */
class HistoryFragment : Fragment() {

    lateinit var binding: FragmentHistoryBinding

    /**
     * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
     * initialize the components of the app to be used instead of looking for them each time we need to use those views
     * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
     *  so that we don't lose that specific state*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.d("TAG", "message")
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_history, container, false)

        binding.backFromOrderHistory.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_historyFragment_to_welcomeFragment)
        }
        //getting recyclerview from xml
        val recyclerView = binding.histRecyclerView

        //adding a layoutmanager
        recyclerView.layoutManager = LinearLayoutManager(context)

        binding.histRecyclerView.setHasFixedSize(true)

        //creating our adapter
        val adapter = HistAdapter()

        var viewModel = ViewModelProviders.of(this).get(HistViewModel::class.java)
        // listen to the viewModel’s data and
        // attach it to the adapter
        viewModel.getHistory().observe(this, object: Observer<List<OrderHistory>> {
            // store a references to the adapter
            private val adapter = HistAdapter()

            override fun onChanged(t: List<OrderHistory>?) {
                if (t != null) {
                    Log.d("TAG", t.toString())
                    adapter.setHistory(t)
                }
            }
        })




        //now adding the adapter to recyclerview
        recyclerView.adapter = adapter

            return binding.root
    }
}
