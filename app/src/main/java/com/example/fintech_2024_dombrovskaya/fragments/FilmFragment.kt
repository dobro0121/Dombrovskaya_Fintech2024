package com.example.fintech_2024_dombrovskaya.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.fintech_2024_dombrovskaya.R
import com.squareup.picasso.Picasso


class FilmFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val image = view.findViewById<ImageView>(R.id.imageViewPosterIn)
        val name = view.findViewById<TextView>(R.id.textViewFilmTitle)
        val genre = view.findViewById<TextView>(R.id.textViewFilmGenres)
        val country = view.findViewById<TextView>(R.id.textViewFilmCountries)
        val year = view.findViewById<TextView>(R.id.textViewFilmYear)
        val description = view.findViewById<TextView>(R.id.textViewFilmDescription)

        arguments?.getString("image")?.let { Picasso.get().load(it).into(image) }
        name.text = arguments?.getString("name")
        genre.text = "Жанры: " + arguments?.getString("genre")
        country.text = "Страны: " + arguments?.getString("country")
        year.text = "Год: " + arguments?.getString("year")
        description.text = arguments?.getString("description")
    }

}