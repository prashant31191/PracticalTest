package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practical.databinding.AdapterBannersBinding
import com.practical.databinding.AdapterCategoriesBinding
import com.practical.databinding.AdapterOfferbannerBinding
import com.practical.network.model.BannerSliderModel


class TopBannerAdapter : RecyclerView.Adapter<TopBannerViewHolder>() {
    var listModel = mutableListOf<BannerSliderModel>()
    fun setList(listModel: List<BannerSliderModel>) {
        this.listModel = listModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TopBannerViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterBannersBinding.inflate(inflater, parent, false)
        return TopBannerViewHolder(binding)
    }

    override fun onBindViewHolder(holder: TopBannerViewHolder, position: Int) {
        val product = listModel.get(position)
        Glide.with(holder.itemView.context).load(product.mobile_image).into(holder.binding.imageview)
    }

    override fun getItemCount(): Int {
        return listModel.size
    }
}

class TopBannerViewHolder(val binding: AdapterBannersBinding) : RecyclerView.ViewHolder(binding.root) {
}