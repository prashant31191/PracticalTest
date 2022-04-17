package com.practical.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.practical.network.model.ProductModel
import com.practical.repo.MainRepository
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val bestSellerList = MutableLiveData<ArrayList<ProductModel>>()
    val errorMessage = MutableLiveData<String>()


    fun getHomeDetails() {
        Timber.d("getHomeDetails")
        val jsonParams = JsonObject()
        jsonParams.addProperty("customer_id", "")

        val response = repository.getHomeDetails(jsonParams)
        response.enqueue(object : Callback<JsonArray> {
            override fun onResponse(call: Call<JsonArray>, response: Response<JsonArray>) {
                Timber.d("onResponse")
                var mJsonArray = response.body() as JsonArray
                var mJson:JsonObject = mJsonArray.get(0) as JsonObject
                var mJsonData = mJson.get("data")
                val mJSONData = JSONObject(mJsonData.toString())
                Timber.d("mJsonData -$mJsonData")

                var mObjBestSeller = mJSONData.optJSONObject("best_seller")
                var mListBestSeller = mObjBestSeller.getJSONArray("bestseller_list")
                var gson = Gson()

                val mBestSellerList: ArrayList<ProductModel> =
                    gson.fromJson(mListBestSeller.toString(), ArrayList::class.java) as ArrayList<ProductModel>
                //val mBestSellerList = gson.fromJson(mListBestSeller.toString(), ArrayList<ProductModel::class.java>).asList()

                Timber.d("mObjBestSeller-${mBestSellerList.size}")
                bestSellerList.postValue(mBestSellerList)
                //Timber.d(""+response.body())

            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                errorMessage.postValue(t.message)
                Timber.d("onFailure")
                Timber.d(t)
            }
        })
    }

    operator fun <T> MutableLiveData<ArrayList<T>>.plusAssign(values: List<T>) {
        val value = this.value ?: arrayListOf()
        value.addAll(values)
        this.value = value
    }
}