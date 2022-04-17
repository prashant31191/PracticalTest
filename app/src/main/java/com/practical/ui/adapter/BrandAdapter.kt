package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practical.databinding.AdapterBrandsBinding
import com.practical.databinding.AdapterCategoriesBinding
import com.practical.databinding.AdapterOfferbannerBinding
import com.practical.network.model.BrandModel


class BrandAdapter : RecyclerView.Adapter<BrandViewHolder>() {
    var listModel = mutableListOf<BrandModel>()
    fun setList(listModel: List<BrandModel>) {
        this.listModel = listModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BrandViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBrandsBinding.inflate(inflater, parent, false)
        return BrandViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BrandViewHolder, position: Int) {
        val product = listModel.get(position)
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.ivCategory)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }
}

class BrandViewHolder(val binding: AdapterBrandsBinding) : RecyclerView.ViewHolder(binding.root) {
}