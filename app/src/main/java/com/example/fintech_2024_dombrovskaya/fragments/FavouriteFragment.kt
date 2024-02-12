package com.example.fintech_2024_dombrovskaya.fragments

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintech_2024_dombrovskaya.App
import com.example.fintech_2024_dombrovskaya.FilmAdapter
import com.example.fintech_2024_dombrovskaya.viewModel.FragmentViewModel
import com.example.fintech_2024_dombrovskaya.activities.MainActivity
import com.example.fintech_2024_dombrovskaya.R

class FavouriteFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private lateinit var fragmentViewModel: FragmentViewModel
    private lateinit var adapter: FilmAdapter


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
        fragmentViewModel = ViewModelProvider(this,
            FragmentViewModel.ViewModelFactory(application, repository)
        )
            .get(FragmentViewModel::class.java)

        return inflater.inflate(R.layout.fragment_favourite, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar = activity?.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        topAppBar?.title = "Избранное"

        val progressBarFav = view.findViewById<ProgressBar>(R.id.progressBarFav)
        progressBarFav.visibility = View.VISIBLE

        val recyclerViewFilms: RecyclerView = view.findViewById(R.id.recyclerViewFavourite)
        adapter = FilmAdapter()

        recyclerViewFilms.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFilms.adapter = adapter
        recyclerViewFilms.setHasFixedSize(true)

        fragmentViewModel.getFavouriteDataList().observe(viewLifecycleOwner) { dataList ->
            adapter.submitList(dataList)
            progressBarFav.visibility = View.INVISIBLE
        }

        adapter.setOnItemLongClickListener(object : FilmAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                val film = adapter.currentList[position]
                film.isFavourite = film.isFavourite != true
                fragmentViewModel.updateFilm(film)

                fragmentViewModel.getFavouriteDataList().observe(viewLifecycleOwner) { dataList ->
                    adapter.submitList(dataList)
                }
            }
        })

        val buttonPopularFragment = mainActivity.findViewById<Button>(R.id.button_popular)
        val buttonFavouriteFragment = mainActivity.findViewById<Button>(R.id.button_favourites)

        buttonPopularFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_favouriteFragment_to_popularFragment)
        }

        buttonFavouriteFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_favouriteFragment_self)
        }
    }
}