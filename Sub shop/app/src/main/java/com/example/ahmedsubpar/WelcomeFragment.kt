package com.example.ahmedsubpar

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.ahmedsubpar.databinding.FragmentWelcomeBinding


/**
 * welcome fragment will give the user the choice to make an order or check the previous orders
 */
class WelcomeFragment : Fragment() {


    /**
     * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
     * initialize the components of the app to be used instead of looking for them each time we need to use those views
     * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
     *  so that we don't lose that specific state*/
    override fun onCreateView( inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        val binding : FragmentWelcomeBinding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_welcome, container, false)

        binding.orderButton.setOnClickListener{ view: View ->
            view.findNavController().navigate(R.id.action_welcomeFragment3_to_orderFragment)
        }

        return binding.root
    }
    }
