package com.example.keeptruckin.module.home.citySearch

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeptruckin.AppApplication
import com.example.keeptruckin.BaseViewModelActivity
import com.example.keeptruckin.R
import com.example.keeptruckin.di.component.DaggerCitySearchComponent
import com.example.keeptruckin.di.module.CitySearchModule
import javax.inject.Inject

fun getCitySearchIntent(context: Context): Intent {
    val intent = Intent(context, CitySearchActivity::class.java)
    return intent
}

class CitySearchActivity : BaseViewModelActivity() {
    @Inject
    lateinit var citySearchViewModel: CitySearchViewModel
    lateinit var binding: com.example.keeptruckin.databinding.ActivityCitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_search)
        setupFragmentComponent()
        initComponents()
        setToolbar()
    }

    fun initComponents() {
        citySearchViewModel.apply {
            binding.viewModel = this
            bindViewModel(this)
        }
        binding.apply{
           viewModel = citySearchViewModel
            lifecycleOwner = this@CitySearchActivity
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)

            }

        }
    }

    override fun setupFragmentComponent() {
        DaggerCitySearchComponent.builder()
            .applicationComponent(AppApplication.getInstance()?.mComponent)
            .citySearchModule(CitySearchModule(this))
            .build().inject(this)
    }
}