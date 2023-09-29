package com.code93.e_movie.ui.home.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.code93.e_movie.R
import com.code93.e_movie.domain.model.ResultModel

class MovieAdapter(
    private var listResultModel: List<ResultModel>,
    private val onItemSelected: (ResultModel) -> Unit
) : Adapter<MovieViewHolder>() {

    fun setListResultModel(listResultModel: List<ResultModel>) {
        this.listResultModel = listResultModel
        notifyDataSetChanged()
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        return MovieViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
        )
    }

    override fun getItemCount(): Int = listResultModel.size

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(listResultModel[position], onItemSelected)
    }
}