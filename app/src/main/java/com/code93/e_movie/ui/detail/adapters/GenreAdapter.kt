package com.code93.e_movie.ui.detail.adapters


import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code93.e_movie.R
import com.code93.e_movie.domain.model.GenreMovieModel

class GenreAdapter(
    private var listGenre: List<GenreMovieModel>
) : RecyclerView.Adapter<GenreViewHolder>() {

    fun setListGenre(listGenre: List<GenreMovieModel>) {
        this.listGenre = listGenre
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GenreViewHolder {
        return GenreViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre, parent, false),
            parent.context
        )
    }

    override fun getItemCount(): Int = listGenre.size

    override fun onBindViewHolder(holder: GenreViewHolder, position: Int) {
        holder.bind(listGenre[position])
    }
}