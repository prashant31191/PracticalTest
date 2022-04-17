package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practical.databinding.AdapterCategoriesBinding
import com.practical.databinding.AdapterOfferbannerBinding
import com.practical.network.model.OfferBannerModel


class OfferBannerAdapter : RecyclerView.Adapter<OfferBannerViewHolder>() {
    var listModel = mutableListOf<OfferBannerModel>()
    fun setList(listModel: List<OfferBannerModel>) {
        this.listModel = listModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferBannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterOfferbannerBinding.inflate(inflater, parent, false)
        return OfferBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: OfferBannerViewHolder, position: Int) {
        val product = listModel.get(position)
        //holder.binding.tvCategoryName?.setText(product.name)
        Glide.with(holder.itemView.context).load(product.banner_image).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }
}

class OfferBannerViewHolder(val binding: AdapterOfferbannerBinding) : RecyclerView.ViewHolder(binding.root) {
}