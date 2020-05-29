package com.example.keeptruckin.module.home.citySearch

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeptruckin.R
import javax.inject.Inject

class CitySearchActivity : AppCompatActivity() {
    @Inject
    lateinit var citySearchViewModel: CitySearchViewModel
    lateinit var binding: com.example.keeptruckin.databinding.ActivityCitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_search)
        setupFragmentComponent()
        initComponents()
    }

    fun initComponents() {
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        binding.apply{
           viewModel = citySearchViewModel
            lifecycleOwner = this@CitySearchActivity
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)

            }

        }
    }

    fun setupFragmentComponent() {

    }
}