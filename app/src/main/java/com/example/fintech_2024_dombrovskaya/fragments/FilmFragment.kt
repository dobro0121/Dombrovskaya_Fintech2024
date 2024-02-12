package com.example.fintech_2024_dombrovskaya.fragments

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Typeface
import android.os.Bundle
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StyleSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.widget.AppCompatImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.fintech_2024_dombrovskaya.R
import com.example.fintech_2024_dombrovskaya.activities.MainActivity
import com.squareup.picasso.Picasso


class FilmFragment : Fragment() {

    lateinit var mainActivity: MainActivity
    override fun onAttach(context: Context) {
        super.onAttach(context)
        mainActivity = context as MainActivity
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_film, container, false)
    }

    @SuppressLint("SetTextI18n")
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
        description.text = arguments?.getString("description")

        val spannableGenres = SpannableString("Жанры: ${arguments?.getString("genre")}")
        val spannableCountries = SpannableString("Страна: ${arguments?.getString("country")}")
        val spannableYear = SpannableString("Год: ${arguments?.getString("year")}")

        spannableGenres.setSpan(StyleSpan(Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableCountries.setSpan(StyleSpan(Typeface.BOLD), 0, 7, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spannableYear.setSpan(StyleSpan(Typeface.BOLD), 0, 4, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)

        genre.text = spannableGenres
        country.text = spannableCountries
        year.text = spannableYear

        val buttonBack = view.findViewById<AppCompatImageButton>(R.id.imageButtonBackFilms)

        buttonBack.setOnClickListener {
            val fm: FragmentManager = mainActivity.supportFragmentManager
            fm.popBackStack()
        }
    }
}