package com.practical.network.model

import kotlinx.serialization.Serializable

//shop_by_brand
@Serializable
data class BrandModel (
    var brand_id: String,
    var image: String,
)