package com.example.fintech_2024_dombrovskaya.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintech_2024_dombrovskaya.API.ApiService
import com.example.fintech_2024_dombrovskaya.App
import com.example.fintech_2024_dombrovskaya.FilmAdapter
import com.example.fintech_2024_dombrovskaya.PopularViewModel
import com.example.fintech_2024_dombrovskaya.activities.MainActivity
import com.example.fintech_2024_dombrovskaya.R
import kotlinx.coroutines.launch

class PopularFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private lateinit var viewModel: PopularViewModel
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val application = requireActivity().application as App
        val repository = application.popularRepository
        viewModel = ViewModelProvider(this,
            PopularViewModel.PopularViewModelFactory(application, repository)
        )
            .get(PopularViewModel::class.java)

        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getListOfPopularFilms()

        val recyclerViewFilms: RecyclerView = view.findViewById(R.id.recyclerViewPopular)
        val adapter = FilmAdapter()

        recyclerViewFilms.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFilms.adapter = adapter
        recyclerViewFilms.setHasFixedSize(true)

        viewModel.getNewDataList().observe(viewLifecycleOwner) { dataList ->
            adapter.submitList(dataList)
        }

        val buttonPopularFragment = mainActivity.findViewById<Button>(R.id.button_popular)
        val buttonFavouriteFragment = mainActivity.findViewById<Button>(R.id.button_favourites)

        buttonPopularFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_popularFragment_self)
        }

        buttonFavouriteFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_popularFragment_to_favouriteFragment)
        }

    }
}