package com.practical.network.model

import kotlinx.serialization.Serializable

//see_more_categories
@Serializable
data class SeeMoreCategoryModel (
    var name: String,
    var image: String,
    var entity_id: String,
)