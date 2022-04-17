package com.practical.network.model

//best_seller.bestseller_list & trending_category
data class ProductModel(
    val product_id: String,
    val image: String,
    val name: String,
    val price: String,
    val currency_code: String,

    val mgs_brand: String,
    val special_price: String,
    val discount: String,

)