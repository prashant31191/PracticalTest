package com.practical.ui.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.practical.databinding.AdapterBannersBinding
import com.practical.databinding.AdapterGetGlamBinding
import com.practical.network.model.ProductModel


class GetGlamAdapter : RecyclerView.Adapter<GlamViewHolder>() {
    var listProductModel = mutableListOf<ProductModel>()
    fun setList(listProductModel: List<ProductModel>) {
        this.listProductModel = listProductModel.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GlamViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterGetGlamBinding.inflate(inflater, parent, false)
        return GlamViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GlamViewHolder, position: Int) {
        val product = listProductModel.get(position)
        Glide.with(holder.itemView.context).load(product.image).into(holder.binding.ivPhoto)
        holder.binding.tvBrandName.setText(product.mgs_brand)
        holder.binding.tvProductName.setText(product.name)
        holder.binding.tvPrice.setText(product.currency_code +" "+product.price)
        holder.binding.tvFinalPrice.setText(product.currency_code +" "+product.special_price)
        holder.binding.tvDiscount.setText("("+product.discount+")")
    }

    override fun getItemCount(): Int {
        return listProductModel.size
    }
}

class GlamViewHolder(val binding: AdapterGetGlamBinding) : RecyclerView.ViewHolder(binding.root) {
}