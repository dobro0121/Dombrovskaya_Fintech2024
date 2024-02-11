package com.example.fintech_2024_dombrovskaya

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.Navigation

class FavouriteFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val buttonPopularFragment = mainActivity.findViewById<Button>(R.id.button_popular)
        val buttonFavouriteFragment = mainActivity.findViewById<Button>(R.id.button_favourite)

        buttonPopularFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_favouriteFragment_to_popularFragment)
        }

        buttonFavouriteFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_favouriteFragment_self)
        }

    }
}