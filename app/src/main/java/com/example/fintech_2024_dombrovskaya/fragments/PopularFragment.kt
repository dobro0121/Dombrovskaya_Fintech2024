package com.example.fintech_2024_dombrovskaya.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ProgressBar
import android.widget.SearchView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.fintech_2024_dombrovskaya.App
import com.example.fintech_2024_dombrovskaya.FilmAdapter
import com.example.fintech_2024_dombrovskaya.viewModel.FragmentViewModel
import com.example.fintech_2024_dombrovskaya.R
import com.example.fintech_2024_dombrovskaya.activities.MainActivity
import com.example.fintech_2024_dombrovskaya.database.FilmEntity


class PopularFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    private lateinit var fragmentViewModel: FragmentViewModel
    private var mutableListFilms = mutableListOf<FilmEntity>()
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

        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_popular, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val topAppBar = activity?.findViewById<com.google.android.material.appbar.MaterialToolbar>(R.id.topAppBar)
        topAppBar?.title = "Популярные"

        val progressBar = view.findViewById<ProgressBar>(R.id.progressBarPop)
        progressBar.visibility = View.VISIBLE
        fragmentViewModel.getListOfPopularFilms()

        val recyclerViewFilms: RecyclerView = view.findViewById(R.id.recyclerViewPopular)
        adapter = FilmAdapter()

        recyclerViewFilms.layoutManager = LinearLayoutManager(requireContext())
        recyclerViewFilms.adapter = adapter
        recyclerViewFilms.setHasFixedSize(true)

        fragmentViewModel.getNewDataList().observe(viewLifecycleOwner) { dataList ->
            adapter.submitList(dataList)
            progressBar.visibility = View.INVISIBLE
            mutableListFilms = dataList
        }

        adapter.setOnItemLongClickListener(object : FilmAdapter.OnItemLongClickListener {
            override fun onItemLongClick(position: Int) {
                val film = adapter.currentList[position]
                film.isFavourite = film.isFavourite != true
                fragmentViewModel.updateFilm(film)
                adapter.notifyItemChanged(position)
            }
        })

        val buttonPopularFragment = mainActivity.findViewById<Button>(R.id.button_popular)
        val buttonFavouriteFragment = mainActivity.findViewById<Button>(R.id.button_favourites)

        buttonPopularFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_popularFragment_self)
        }

        buttonFavouriteFragment.setOnClickListener {
            Navigation.findNavController(view).navigate(R.id.action_popularFragment_to_favouriteFragment)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)

        inflater.inflate(R.menu.top_bar, menu)
        val searchView = SearchView((context as MainActivity).supportActionBar?.themedContext ?: context)
        menu.findItem(R.id.search_action).apply {
            setShowAsAction(MenuItem.SHOW_AS_ACTION_COLLAPSE_ACTION_VIEW or MenuItem.SHOW_AS_ACTION_IF_ROOM)
            actionView = searchView
        }

        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String): Boolean {
                filterList(newText)
                return false
            }
        })
    }

    @SuppressLint("NotifyDataSetChanged")
    fun filterList(query: String?) {
        val notFoundLabel = mainActivity.findViewById<TextView>(R.id.textViewNotSearched)

        if (query != null) {
            val filteredList = ArrayList<FilmEntity>()
            for (film in mutableListFilms) {
                if (film.filmName?.contains(query) == true) {
                    filteredList.add(film)
                }
            }
            if (filteredList.isEmpty()) {
                notFoundLabel.visibility = View.GONE
            } else {
                adapter.setFilteredFilms(filteredList)
                notFoundLabel.visibility = View.VISIBLE
            }
        }

    }
}