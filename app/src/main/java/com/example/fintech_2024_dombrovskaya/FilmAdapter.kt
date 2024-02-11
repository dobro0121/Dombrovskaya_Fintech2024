package com.example.fintech_2024_dombrovskaya

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintech_2024_dombrovskaya.database.FilmEntity
import com.squareup.picasso.Picasso


class FilmAdapter:
    ListAdapter<FilmEntity, FilmAdapter.FilmViewHolder>(ItemDiffUtil()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilm = currentList[position]
        holder.bind(currentFilm)

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", currentFilm.filmName)
            var genres = ""
            if(currentFilm.genre!!.isNotEmpty()) {
                for(genre in currentFilm.genre) {
                    genres = "$genres$genre "
                }
            }
            bundle.putString("genre", genres)

            var countries = ""
            if(currentFilm.country!!.isNotEmpty()) {
                for(country in currentFilm.country) {
                    countries = "$countries$country "
                }
            }
            bundle.putString("country", countries)

            bundle.putString("year", currentFilm.year)

            bundle.putString("description", currentFilm.description)

            bundle.putString("image", currentFilm.image)

            holder.itemView.findNavController().navigate(R.id.action_popularFragment_to_filmFragment, bundle)
        }
    }

    override fun getItemCount() = currentList.size

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewNameOfFilm: TextView = itemView.findViewById(R.id.textViewNameOfFilm)
        private val textViewGenre: TextView = itemView.findViewById(R.id.textViewGenre)

        fun bind(film: FilmEntity) {
            Picasso.get()
                .load(film.image)
                .resize(120, 150)
                .centerCrop()
                .into(imageViewPoster)
            textViewNameOfFilm.text = film.filmName
            textViewGenre.text = film.genre + " (" + film.year + ")"
        }
    }

    class ItemDiffUtil: DiffUtil.ItemCallback<FilmEntity>() {
        override fun areItemsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
            return oldItem == newItem
        }

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(oldItem: FilmEntity, newItem: FilmEntity): Boolean {
            return oldItem == newItem
        }
    }
}
