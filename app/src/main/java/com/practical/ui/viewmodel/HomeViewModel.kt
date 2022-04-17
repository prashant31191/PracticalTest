package com.practical.ui.viewmodel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.practical.network.model.BrandModel
import com.practical.network.model.OfferBannerModel
import com.practical.network.model.ProductModel
import com.practical.network.model.SeeMoreCategoryModel
import com.practical.repo.MainRepository
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import timber.log.Timber

class HomeViewModel constructor(private val repository: MainRepository)  : ViewModel() {

    val bestSellerList = MutableLiveData<ArrayList<ProductModel>>()
    val seeMoreCategoryModelList = MutableLiveData<ArrayList<SeeMoreCategoryModel>>()
    val offerBannerModellList = MutableLiveData<ArrayList<OfferBannerModel>>()
    val brandModelList = MutableLiveData<ArrayList<BrandModel>>()
    val errorMessage = MutableLiveData<String>()
    val isLoading = MutableLiveData<Boolean>()


    fun getHomeDetails() {
        isLoading.postValue(true)
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
                var mListMoreCategories = mJSONData.getJSONArray("see_more_categories")
                var mListOfferBanner = mJSONData.getJSONArray("offer_items_banner")
                var mListBrand = mJSONData.getJSONArray("shop_by_brand")

                var gson = Gson()
                var mArrayList = ArrayList<ProductModel>()
                val arr = gson.fromJson(mListBestSeller.toString(), Array<ProductModel>::class.java)
                mArrayList.addAll(arr)
                Timber.d("mArrayList-${mArrayList.size}")
                bestSellerList.postValue(mArrayList)


                var mArrayListMoreCategory = ArrayList<SeeMoreCategoryModel>()
                val arrMoreCategory = gson.fromJson(mListMoreCategories.toString(), Array<SeeMoreCategoryModel>::class.java)
                mArrayListMoreCategory.addAll(arrMoreCategory)
                Timber.d("mArrayListMoreCategory-${mArrayListMoreCategory.size}")
                seeMoreCategoryModelList.postValue(mArrayListMoreCategory)

                var mArrayListOfferBannerModel = ArrayList<OfferBannerModel>()
                val arrOfferBannerModel = gson.fromJson(mListOfferBanner.toString(), Array<OfferBannerModel>::class.java)
                mArrayListOfferBannerModel.addAll(arrOfferBannerModel)
                Timber.d("mArrayListOfferBannerModel-${mArrayListOfferBannerModel.size}")
                offerBannerModellList.postValue(mArrayListOfferBannerModel)

                var mArrayBrandModel = ArrayList<BrandModel>()
                val arrBrandModel = gson.fromJson(mListBrand.toString(), Array<BrandModel>::class.java)
                mArrayBrandModel.addAll(arrBrandModel)
                Timber.d("mArrayBrandModel-${mArrayBrandModel.size}")
                brandModelList.postValue(mArrayBrandModel)

                isLoading.postValue(false)

            }
            override fun onFailure(call: Call<JsonArray>, t: Throwable) {
                errorMessage.postValue(t.message)
                isLoading.postValue(false)
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