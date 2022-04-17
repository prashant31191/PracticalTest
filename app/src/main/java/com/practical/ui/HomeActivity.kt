package com.practical.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.practical.R
import com.practical.databinding.ActivityHomeBinding
import com.practical.network.RetrofitService
import com.practical.repo.MainRepository
import com.practical.repo.MyViewModelFactory
import com.practical.ui.adapter.MainAdapter
import com.practical.ui.viewmodel.HomeViewModel
import timber.log.Timber

class HomeActivity : AppCompatActivity() {
    private val TAG = "HomeActivity"
    private lateinit var binding: ActivityHomeBinding
    lateinit var viewModel: HomeViewModel
    private val retrofitService = RetrofitService.getInstance()
    val adapter = MainAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this, MyViewModelFactory(MainRepository(retrofitService)))
            .get(HomeViewModel::class.java)

        binding.rvBestSeller.adapter = adapter
        viewModel.movieList.observe(this, Observer {
            Timber.d(TAG, "onCreate: $it")
            //adapter.setMovieList(it)
        })

        viewModel.errorMessage.observe(this, Observer {
        })

        viewModel.getHomeDetails()
    }
}