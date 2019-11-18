package com.example.ahmedsubpar

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Build.*
import android.os.Bundle
import android.widget.Toast
import android.view.*
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.ahmedsubpar.databinding.FragmentReceiptBinding

/**
 * Receipt fragment will contain the description of the user order
 */
class ReceiptFragment : Fragment() {

    var orderdetails: OrderDetails = OrderDetails()
    lateinit var binding: FragmentReceiptBinding
    var image_uri: Uri? = null
    var order_text: String = ""

    /**
     * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
     * initialize the components of the app to be used instead of looking for them each time we need to use those views
     * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
     *  so that we don't lose that specific state*/
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_receipt, container, false)

        setHasOptionsMenu(true)
        binding.doneButton.setOnClickListener { view: View ->
            view.findNavController().navigate(R.id.action_receiptFragment_to_welcomeFragment)
        }

        val args = ReceiptFragmentArgs.fromBundle(arguments!!)
        orderdetails = args.orderDetails
        if (savedInstanceState != null) {
            var extra = savedInstanceState.getParcelable<OrderDetails>("orderDetails")
            if (extra != null) orderdetails = extra
        }
        if (orderdetails.toppings.isEmpty()) {
            order_text = "${args.orderDetails.sandwich_type} on ${args.orderDetails.bread_type} \n Without toppings\nTotal price: \$${args.orderDetails.total_price}"
            binding.orderDescription.text = order_text
        } else {
            order_text = "${args.orderDetails.sandwich_type} on ${args.orderDetails.bread_type} \n with: \n${args.orderDetails.toppings}Total price: \$${args.orderDetails.total_price}"
            binding.orderDescription.text = order_text
        }
        return  binding.root
    }

    /**
     * onSaveInstanceState is used to save an instance of the application in order to avoid mall functioning
     */
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putParcelable("orderDetails", orderdetails)
    }

    /**
     * called when the menu is being created
     */
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.share_menu, menu)
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            // hide the menu item if it doesn't resolve
            menu.findItem(R.id.share_item)?.isVisible = false
        }
    }

    /**
     * this function is called when selecting a specific item fronm the menu
     * @param item the selected item
     */
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (VERSION.SDK_INT >= VERSION_CODES.M){
            if (context?.let { ContextCompat.checkSelfPermission(it,  Manifest.permission.READ_EXTERNAL_STORAGE) } ==
                PackageManager.PERMISSION_DENIED){
                //permission denied
                val permissions = arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE);
                //show popup to request runtime permission
                requestPermissions(permissions, PERMISSION_CODE);
            }
            else{
                //permission already granted
                if (item.itemId == R.id.share_item) {
                    if (binding.sharedImage.isVisible == true) {
                        try {
                            shareImageSuccess()
                        } catch (e: Exception) {
                            e.printStackTrace()
                        }
                    } else {
                        shareSuccess()
                    }
                } else {
                    pickImageFromGallery()
                }
            }
        }
        else{
            //system OS is < Marshmallow
            if (item.itemId == R.id.share_item) {
                if (binding.sharedImage.isVisible == true) {
                    try {
                        shareImageSuccess()
                    } catch (e: Exception) {
                        e.printStackTrace()
                    }
                } else {
                    shareSuccess()
                }
            } else {
                pickImageFromGallery()
            }

        }
        return true
    }

    /**
     * this function picks the image from the gallery
     */
    private fun pickImageFromGallery() {
        //Intent to pick image
        val intent = Intent(Intent.ACTION_PICK)
        intent.type = "image/*"
        startActivityForResult(intent, IMAGE_PICK_CODE)
    }

    companion object {
        //image pick code
        private val IMAGE_PICK_CODE = 1000;
        //Permission code
        private val PERMISSION_CODE = 1001;
    }

    /**
     * handle requested permission result
     * @param requestCode the permission code
     * @param permissions the array of permissions
     * @param grantResults granted permissions
     */
    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        when(requestCode){
            PERMISSION_CODE -> {
                if (grantResults.size >0 && grantResults[0] ==
                    PackageManager.PERMISSION_GRANTED){
                    //permission from popup granted
                    pickImageFromGallery()
                }
                else{
                    //permission from popup denied
                    Toast.makeText(context, "Permission denied", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    /**
     * handles the result of picked image
     */
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (resultCode == Activity.RESULT_OK && requestCode == IMAGE_PICK_CODE){
            image_uri = data?.data
            binding.sharedImage.setImageURI(data?.data)
            binding.sharedImage.visibility = View.VISIBLE
        }
    }

    /**
     * this function constructs the intent for sharing the text
     */
    private fun getShareIntent() : Intent {
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.setType("text/plain")
            .putExtra(Intent.EXTRA_TEXT, order_text)
        shareIntent = Intent.createChooser(shareIntent, "Share text via")
        return shareIntent
    }

    /**
     * this function starts the intent that shares the text
     */
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    /**
     * this function constructs the intent for sharing the image and the text
     */
    private fun getShareImageIntent() : Intent {
        var shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK
        shareIntent.putExtra(Intent.EXTRA_STREAM, image_uri).putExtra(Intent.EXTRA_TEXT, order_text)
        shareIntent.type = "image/*"
        shareIntent = Intent.createChooser(shareIntent, "Share image and text via")
        return shareIntent
    }

    /**
     * this function starts the intent that shares the image alongside the text
     */
    private fun shareImageSuccess() {
        startActivity(getShareImageIntent())
    }
}
