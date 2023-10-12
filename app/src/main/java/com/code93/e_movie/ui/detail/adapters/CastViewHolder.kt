package com.code93.e_movie.ui.detail.adapters

import android.view.View
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.code93.e_movie.databinding.ItemCastBinding
import com.code93.e_movie.domain.model.CastModel

class CastViewHolder(val view: View) : ViewHolder(view) {

    private val binding = ItemCastBinding.bind(view)

    fun bind(castModel: CastModel) {
        binding.tvNameCast.text = castModel.name
        binding.tvNameCharacter.text = castModel.character
        Glide.with(view)
            .load(castModel.profilePath)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .into(binding.ivPhoto)
    }
}