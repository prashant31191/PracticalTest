package com.practical.ui

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2
import com.practical.R
import com.practical.databinding.ActivityHomeBinding
import com.practical.network.RetrofitService
import com.practical.repo.MainRepository
import com.practical.repo.MyViewModelFactory
import com.practical.ui.adapter.*
import com.practical.ui.viewmodel.HomeViewModel

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    private val retrofitService = RetrofitService.getInstance()
    val mBestSallerAdaper = BestSallerAdaper()
    val mMoreCategoryAdapter = MoreCategoryAdapter()
    val mOfferBannerAdapter = OfferBannerAdapter()
    val mGetGlamAdapter = GetGlamAdapter()
    val mBrandAdapter = BrandAdapter()
    val mTopBannerAdapter = TopBannerAdapter()

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
        binding.rvGetGlam.adapter = mGetGlamAdapter

        setupCarousel()


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

        viewModel.bannerSliderModelList.observe(this, {
            mTopBannerAdapter.setList(it)
            binding?.viewPager.adapter = mTopBannerAdapter
        })

        viewModel.getGlamList.observe(this, {
            mGetGlamAdapter.setList(it)
        })
        viewModel.trendingCatName.observe(this, {
            binding?.tvGetGlam?.setText(it)
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

    private fun setupCarousel(){

        binding?.viewPager.offscreenPageLimit = 1

        val nextItemVisiblePx = resources.getDimension(com.intuit.sdp.R.dimen._2sdp)
        val currentItemHorizontalMarginPx = resources.getDimension(com.intuit.sdp.R.dimen._4sdp)
        val pageTranslationX = nextItemVisiblePx + currentItemHorizontalMarginPx
        val pageTransformer = ViewPager2.PageTransformer { page: View, position: Float ->
            page.translationX = -pageTranslationX * position
            page.scaleY = 1 - (0.25f * kotlin.math.abs(position))
            page.alpha = 0.25f + (1 - kotlin.math.abs(position))
        }
        binding?.viewPager.setPageTransformer(pageTransformer)
        val itemDecoration = HorizontalMarginItemDecoration(
            this,
            com.intuit.sdp.R.dimen._4sdp
        )
        binding?.viewPager.addItemDecoration(itemDecoration)
    }
}