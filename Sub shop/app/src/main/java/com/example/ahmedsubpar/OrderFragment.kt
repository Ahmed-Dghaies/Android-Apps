package com.example.ahmedsubpar

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import android.widget.CheckBox
import androidx.databinding.DataBindingUtil
import com.example.ahmedsubpar.databinding.FragmentOrderBinding
import androidx.navigation.findNavController
import android.widget.RadioButton
import androidx.navigation.ui.NavigationUI


/**
 * Order fragment allows the client to pass the order of a specific sandwich
 * @property sandwichPrices an array stating the prices of the available sandwiches
 * @property sandwichType the type of the sandwich selected by the user
 * @property breadType the bread type selected by the user
 * @property binding used to bind views of the layout
 * @property toppings the toppings selected by the user
 * @property numToppings number of toppings
 * @property price the total price of the sandwich
 */
class OrderFragment : Fragment() {

    var sandwichPrices = arrayOf(7.65f,8.0f,9.25f,10.5f)
    private var sandwichType: String = ""
    private var breadType: String = ""
    lateinit var binding: FragmentOrderBinding
    var toppings: String = ""
    var numToppings: Int = 0
    var price: Float = 0.0f
    var orderdetails: OrderDetails = OrderDetails()


    /**
     * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
     * initialize the components of the app to be used instead of looking for them each time we need to use those views
     * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
     *  so that we don't lose that specific state*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_order, container, false)



        binding.orderButton.setOnClickListener { view: View ->
            for (i in 0 until binding.toppingsGroup.childCount) {
                val v = binding.toppingsGroup.getChildAt(i)
                if ((v is CheckBox)&&(v.isChecked)) {
                    toppings += v.text
                    toppings += "\n"
                    numToppings += 1
                }
            }
            for (i in 0 until binding.breadTypeGroup.childCount) {
                val v = binding.breadTypeGroup.getChildAt(i)
                if ((v is RadioButton)&&(v.isChecked)) {
                    breadType = v.text.toString()
                }
            }
            val sandwichNumber: Int = binding. spinner.selectedItemPosition
            price = (sandwichPrices[sandwichNumber] + (0.5 * numToppings)).toFloat()
            sandwichType = binding.run { spinner.selectedItem.toString() }
            sandwichType = sandwichType.substring(0, sandwichType.length - 5)

            orderdetails.sandwich_type = sandwichType
            orderdetails.bread_type = breadType
            orderdetails.num_toppings = numToppings
            orderdetails.toppings = toppings
            orderdetails.total_price = price

            // check if we already have a saved instance
            if (savedInstanceState != null) {
                var extra = savedInstanceState.getParcelable<OrderDetails>("orderDetails")
                if (extra != null) orderdetails = extra
            }

            view.findNavController().navigate(OrderFragmentDirections.actionOrderFragmentToReceiptFragment(orderdetails))
        }


        return binding.root
    }

    /**
     * onSaveInstanceState is used to save an instance of the application in order to avoid mall functioning
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("orderDetails", orderdetails)
    }
}
