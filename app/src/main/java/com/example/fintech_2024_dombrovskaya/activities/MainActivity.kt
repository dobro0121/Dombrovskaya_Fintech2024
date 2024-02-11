package com.example.fintech_2024_dombrovskaya.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams
import android.widget.LinearLayout
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.fragment.app.FragmentContainerView
import androidx.navigation.NavController
import androidx.navigation.findNavController

import com.example.fintech_2024_dombrovskaya.R
import com.google.android.material.appbar.AppBarLayout


class MainActivity : AppCompatActivity() {

    private lateinit var navController: NavController

    lateinit var appBarLayout: AppBarLayout
    lateinit var bottomNavigation: LinearLayout
    lateinit var fragmentContainerView: FragmentContainerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        appBarLayout = this.findViewById(R.id.appBarLayout)
        bottomNavigation = this.findViewById(R.id.bottom_navigation)
        fragmentContainerView = this.findViewById(R.id.fragmentContainerView)
    }

    /*override fun onStart() {
        super.onStart()

        navController = findNavController(R.id.fragmentContainerView)

        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.filmFragment -> {
                    hideToolbarAndBottomNavigation()
                }

                else -> {
                    showToolbarAndBottomNavigation()
                }
            }
        }
    }

    private fun hideToolbarAndBottomNavigation() {
        appBarLayout.visibility = View.GONE
        bottomNavigation.visibility = View.GONE

        fragmentContainerView.layoutParams.height = LayoutParams.MATCH_PARENT

        val marginLayoutParams =
            layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.setMargins(0, 0, 0, 0)

        val layoutParams = binding.fragmentContainerView.layoutParams
        layoutParams.height = ViewGroup.LayoutParams.MATCH_PARENT
        binding.fragmentContainerView.layoutParams = layoutParams
    }

    private fun showToolbarAndBottomNavigation() {
        appBarLayout.visibility = View.VISIBLE
        bottomNavigation.visibility = View.VISIBLE

        fragmentContainerView.layoutParams.height = LayoutParams.WRAP_CONTENT

        val marginLayoutParams =
            fragmentContainerView.layoutParams as ViewGroup.MarginLayoutParams
        marginLayoutParams.setMargins(
            0,
            160,
            0,
          75
        )
    }*/
}