/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.navigation

import android.content.ActivityNotFoundException
import android.content.Intent
import androidx.databinding.DataBindingUtil
import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.core.app.ShareCompat
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.findNavController
import com.example.android.navigation.databinding.FragmentGameWonBinding
import android.content.pm.ResolveInfo
import android.content.pm.PackageManager


/**
 * If the game is won this fragment will appear congratulating the user
 */
class GameWonFragment : Fragment() {

    /**
    * OnCreate method is where the application is initialized it will call the onCreate method from its superclass
    * initialize the components of the app to be used instead of looking for them each time we need to use those views
    * @param savedInstanceState if we save the state of the application we can give it back to the onCreate method
    *  so that we don't lose that specific state*/
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        val binding: FragmentGameWonBinding = DataBindingUtil.inflate(
                inflater, R.layout.fragment_game_won, container, false)
        binding.nextMatchButton.setOnClickListener { view: View ->
            view.findNavController().navigate(
                    GameWonFragmentDirections.actionGameWonFragmentToGameFragment())
        }
        setHasOptionsMenu(true)
        return binding.root
    }

    /**
     * onCreateOptionMenu shows or hide the share menu depending on whether the mobile has compatible apps or not
     * @param menu the menu to shoz
     * @param inflater inflator for the menu
     */
    override fun onCreateOptionsMenu(menu: Menu?, inflater: MenuInflater?) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater?.inflate(R.menu.winner_menu, menu)
        //checks if the activity resolves
        if (null == getShareIntent().resolveActivity(activity!!.packageManager)) {
            //hide the share menu
            menu?.findItem(R.id.share)?.setVisible(false)
        }
    }

    /**
     * getShareIntent get's the args and build the share intent inside
     */
    private fun getShareIntent(): Intent {
        var args = GameWonFragmentArgs.fromBundle(arguments!!)
        return ShareCompat.IntentBuilder.from(activity)
                .setText(getString(R.string.share_success_text, args.numCorrect, args.numQuestions))
                .setType("Text/plain")
                .intent
    }

    /**
     * share success starts the share intent activity
     */
    private fun shareSuccess() {
        startActivity(getShareIntent())
    }

    /**
     * this method will be called when the share icon in the menu is clicked
     * @param item the selected item from the menu
     */
    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.share -> shareSuccess()
        }
        return super.onOptionsItemSelected(item)
    }
}
