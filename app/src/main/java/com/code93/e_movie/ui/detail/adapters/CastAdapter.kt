package com.code93.e_movie.ui.detail.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.code93.e_movie.R
import com.code93.e_movie.domain.model.CastModel

class CastAdapter(
    private var listCast: List<CastModel>
) : RecyclerView.Adapter<CastViewHolder>() {

    fun setListCast(listCast: List<CastModel>) {
        this.listCast = listCast
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CastViewHolder {
        return CastViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_cast, parent, false)
        )
    }

    override fun getItemCount() = listCast.size

    override fun onBindViewHolder(holder: CastViewHolder, position: Int) {
        holder.bind(listCast[position])
    }
}