package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practical.databinding.AdapterCategoriesBinding
import com.practical.network.model.SeeMoreCategoryModel


class MoreCategoryAdapter : RecyclerView.Adapter<MoreCategoryViewHolder>() {
    var listModel = mutableListOf<SeeMoreCategoryModel>()
    fun setList(listModel: List<SeeMoreCategoryModel>) {
        this.listModel = listModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MoreCategoryViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterCategoriesBinding.inflate(inflater, parent, false)
        return MoreCategoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoreCategoryViewHolder, position: Int) {
        val product = listModel.get(position)
        holder.binding.tvCategoryName?.setText(product.name)
        Glide.with(holder.itemView.context).load(product.image).centerCrop().circleCrop().into(holder.binding.ivCategory)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }
}

class MoreCategoryViewHolder(val binding: AdapterCategoriesBinding) : RecyclerView.ViewHolder(binding.root) {
}