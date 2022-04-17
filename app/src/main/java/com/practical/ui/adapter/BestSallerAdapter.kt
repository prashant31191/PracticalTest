package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practical.databinding.AdapterBannersBinding
import com.practical.network.model.ProductModel


class BestSallerAdaper : RecyclerView.Adapter<MainViewHolder>() {
    var listProductModel = mutableListOf<ProductModel>()
    fun setList(listProductModel: List<ProductModel>) {
        this.listProductModel = listProductModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBannersBinding.inflate(inflater, parent, false)
        return MainViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        val product = listProductModel.get(position)
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return listProductModel.size
    }
}

class MainViewHolder(val binding: AdapterBannersBinding) : RecyclerView.ViewHolder(binding.root) {
}