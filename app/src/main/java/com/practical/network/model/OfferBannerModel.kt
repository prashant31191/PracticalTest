package com.practical.network.model

import kotlinx.serialization.Serializable

//banner_slider
@Serializable
data class OfferBannerModel (
    var category_id: String,
    var banner_image: String,
)