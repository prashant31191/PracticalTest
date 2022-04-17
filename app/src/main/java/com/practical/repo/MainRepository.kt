package com.practical.repo

import com.google.gson.JsonObject
import com.practical.network.RetrofitService


class MainRepository constructor(private val retrofitService: RetrofitService) {

    fun getHomeDetails(jsonParams: JsonObject) = retrofitService.getHomeDetails(jsonParams)
}