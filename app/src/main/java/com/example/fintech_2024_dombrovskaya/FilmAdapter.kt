package com.example.fintech_2024_dombrovskaya

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.FragmentManager
import androidx.navigation.findNavController
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.fintech_2024_dombrovskaya.database.FilmEntity
import com.squareup.picasso.Picasso


class FilmAdapter:
    ListAdapter<FilmEntity, FilmAdapter.FilmViewHolder>(ItemDiffUtil()) {

    private var onItemLongClickListener: OnItemLongClickListener? = null

    interface OnItemLongClickListener {
        fun onItemLongClick(position: Int)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FilmViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.film_item, parent, false)
        return FilmViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: FilmViewHolder, position: Int) {
        val currentFilm = currentList[position]
        holder.bind(currentFilm)

        if (currentFilm.isFavourite == true) {
            holder.imageButtonRating.visibility = View.VISIBLE
        } else {
            holder.imageButtonRating.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val bundle = Bundle()
            bundle.putString("name", currentFilm.filmName)

            bundle.putString("genre", currentFilm.genre)

            bundle.putString("country", currentFilm.country)

            bundle.putString("year", currentFilm.year)

            bundle.putString("description", currentFilm.description)

            bundle.putString("image", currentFilm.image)

            if (holder.itemView.findNavController().currentDestination?.id != R.id.favouriteFragment) {
                holder.itemView.findNavController().navigate(R.id.action_popularFragment_to_filmFragment, bundle)
            } else {
                holder.itemView.findNavController().navigate(R.id.action_favouriteFragment_to_filmFragment, bundle)
            }
        }

        holder.itemView.setOnLongClickListener {
            val pos = holder.adapterPosition
            onItemLongClickListener?.onItemLongClick(pos)
            true
        }
    }

    fun setOnItemLongClickListener(listener: OnItemLongClickListener) {
        onItemLongClickListener = listener
    }

    override fun getItemCount() = currentList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setFilteredFilms(filterFilms: List<FilmEntity>) {
        submitList(filterFilms.toMutableList())
        notifyDataSetChanged()
    }

    class FilmViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val imageViewPoster: ImageView = itemView.findViewById(R.id.imageViewPoster)
        private val textViewNameOfFilm: TextView = itemView.findViewById(R.id.textViewNameOfFilm)
        private val textViewGenre: TextView = itemView.findViewById(R.id.textViewGenre)
        val imageButtonRating: ImageButton = itemView.findViewById(R.id.imageButtonRating)

        fun bind(film: FilmEntity) {
            Picasso.get()
                .load(film.image)
                .resize(200, 300)
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
