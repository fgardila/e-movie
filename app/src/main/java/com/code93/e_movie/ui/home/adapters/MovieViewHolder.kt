package com.code93.e_movie.ui.home.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import coil.load
import com.code93.e_movie.databinding.ItemMovieBinding
import com.code93.e_movie.domain.model.ResultModel

class MovieViewHolder(private val view: View) : ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(resultModel: ResultModel, onItemSelected: (resultModel: ResultModel) -> Unit) {

        binding.ivMovie.load(
            resultModel.posterPath
        )

        binding.container.setOnClickListener {
            onItemSelected(resultModel)
        }
    }
}