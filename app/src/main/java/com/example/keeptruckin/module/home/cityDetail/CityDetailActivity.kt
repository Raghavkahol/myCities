package com.example.keeptruckin.module.home.cityDetail

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeptruckin.R
import javax.inject.Inject

class CityDetailActivity : AppCompatActivity() {
    @Inject
    lateinit var cityDetailViewModel: CityDetailViewModel
    lateinit var binding: com.example.keeptruckin.databinding.ActivityCityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_details)
        setupFragmentComponent()
        initComponents()
    }

    fun initComponents() {
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
       binding.apply {
           viewModel = cityDetailViewModel
           lifecycleOwner = this@CityDetailActivity
       }
    }

    fun setupFragmentComponent() {

    }
}