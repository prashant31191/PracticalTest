package com.practical.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.JsonObject
import com.practical.repo.MainRepository
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel constructor(private val repository: MainRepository, )  : ViewModel() {

    val movieList = MutableLiveData<JsonObject>()
    val errorMessage = MutableLiveData<String>()

    fun getHomeDetails() {
        Timber.d("getHomeDetails")
        val jsonParams = JsonObject()
        jsonParams.addProperty("customer_id", "")

        val response = repository.getHomeDetails(jsonParams)
        response.enqueue(object : Callback<JsonObject> {
            override fun onResponse(call: Call<JsonObject>, response: Response<JsonObject>) {
                Timber.d("onResponse")
                movieList.postValue(response.body())

            }
            override fun onFailure(call: Call<JsonObject>, t: Throwable) {
                errorMessage.postValue(t.message)
                Timber.d("onFailure")
                Timber.d(t)
            }
        })
    }
}