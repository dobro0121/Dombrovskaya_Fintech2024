package com.example.fintech_2024_dombrovskaya

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import kotlinx.coroutines.coroutineScope
import kotlinx.coroutines.launch

class PopularFragment : Fragment() {

    lateinit var mainActivity: MainActivity

    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    private val apiService by lazy {
        ApiService.create()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        lifecycleScope.launch {
            apiService.getDescriptionOfFilm(5275429)
        }

        val buttonPopularFragment = mainActivity.findViewById<Button>(R.id.button_popular)
        val buttonFavouriteFragment = mainActivity.findViewById<Button>(R.id.button_favourite)

        buttonPopularFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_popularFragment_self)
        }

        buttonFavouriteFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_popularFragment_to_favouriteFragment)
        }

    }
}