package com.practical.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import com.practical.R
import com.practical.databinding.ActivityHomeBinding
import com.practical.network.RetrofitService
import com.practical.repo.MainRepository
import com.practical.repo.MyViewModelFactory
import com.practical.ui.adapter.BestSallerAdaper
import com.practical.ui.adapter.BrandAdapter
import com.practical.ui.adapter.MoreCategoryAdapter
import com.practical.ui.adapter.OfferBannerAdapter
import com.practical.ui.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    private val retrofitService = RetrofitService.getInstance()
    val mBestSallerAdaper = BestSallerAdaper()
    val mMoreCategoryAdapter = MoreCategoryAdapter()
    val mOfferBannerAdapter = OfferBannerAdapter()
    val mBrandAdapter = BrandAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService)))
            .get(HomeViewModel::class.java)

        binding.rvBestSeller.adapter = mBestSallerAdaper
        binding.rvCategories.adapter = mMoreCategoryAdapter
        binding.rvOfferBanner.adapter = mOfferBannerAdapter
        binding.rvBrands.adapter = mBrandAdapter

        viewModel.bestSellerList.observe(this, {
            binding?.tvNoData?.visibility = View.GONE
            mBestSallerAdaper.setList(it)
        })

        viewModel.seeMoreCategoryModelList.observe(this, {
            mMoreCategoryAdapter.setList(it)
        })

        viewModel.offerBannerModellList.observe(this, {
            mOfferBannerAdapter.setList(it)
        })

        viewModel.brandModelList.observe(this, {
            mBrandAdapter.setList(it)
        })

        binding?.tvNoData?.visibility = View.VISIBLE
        binding?.tvNoData?.setOnClickListener {
            viewModel.getHomeDetails()
        }


        viewModel.errorMessage.observe(this, {
            binding?.tvNoData?.visibility = View.VISIBLE
        })

        viewModel.isLoading.observe(this, {
             binding?.mProgressBar?.visibility = if(it) View.VISIBLE else View.GONE
             binding?.vLoading?.visibility = if(it) View.VISIBLE else View.GONE
        })

        viewModel.getHomeDetails()
    }
}