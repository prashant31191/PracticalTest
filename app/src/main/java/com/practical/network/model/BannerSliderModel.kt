package com.practical.network.model

import kotlinx.serialization.Serializable

//banner_slider
@Serializable
data class BannerSliderModel (
    var slide_id: String,
    var mobile_image: String,
)