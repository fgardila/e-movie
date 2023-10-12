package com.code93.e_movie.ui.home.adapters

import android.graphics.drawable.Drawable
import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.RequestListener
import com.code93.e_movie.R
import com.code93.e_movie.databinding.ItemMovieBinding
import com.code93.e_movie.domain.model.ResultModel
import com.code93.e_movie.domain.util.ImageUtil

class MovieViewHolder(private val view: View) : ViewHolder(view) {

    private val binding = ItemMovieBinding.bind(view)

    fun bind(resultModel: ResultModel, onItemSelected: (resultModel: ResultModel) -> Unit) {

        Glide.with(view.context)
            .load(resultModel.posterPath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .listener(object : RequestListener<Drawable> {
                override fun onLoadFailed(
                    e: GlideException?,
                    model: Any?,
                    target: Target<Drawable>?,
                    isFirstResource: Boolean
                ): Boolean {
                    binding.ivMovie.setImageBitmap(
                        ImageUtil.convert(resultModel.bitmapString)
                    )
                    return true
                }

                override fun onResourceReady(
                    resource: Drawable?,
                    model: Any?,
                    target: Target<Drawable>?,
                    dataSource: DataSource?,
                    isFirstResource: Boolean
                ): Boolean {
                    return false
                }

            })
            .into(binding.ivMovie)

        binding.container.setOnClickListener {
            onItemSelected(resultModel)
        }
    }
}