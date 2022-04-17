package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.practical.databinding.AdapterBannersBinding
import com.practical.network.model.BannerSliderModel

class MainAdapter : RecyclerView.Adapter<MainViewHolder>() {
    var movies = mutableListOf<BannerSliderModel>()
    fun setMovieList(movies: List<BannerSliderModel>) {
        this.movies = movies.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBannersBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val movie = movies[position]
        holder.binding.name.text = movie.name
        Glide.with(holder.itemView.context).load(movie.imageUrl).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return movies.size
    }
}

class MainViewHolder(val binding: AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {
}