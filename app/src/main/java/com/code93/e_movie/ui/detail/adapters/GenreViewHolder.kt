package com.code93.e_movie.ui.detail.adapters

import android.content.Context
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.code93.e_movie.data.network.response.Genre
import com.code93.e_movie.databinding.ItemGenreBinding
import com.code93.e_movie.domain.model.GenreMovieModel

class GenreViewHolder(
    view: View,
    private val context: Context
) : ViewHolder(view) {

    private val binding = ItemGenreBinding.bind(view)

    fun bind(genre: GenreMovieModel) {
        binding.tvGenre.text = context.getText(genre.nameResource)
    }
}