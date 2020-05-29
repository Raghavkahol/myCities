package com.example.keeptruckin.module.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import com.example.keeptruckin.AppApplication
import com.example.keeptruckin.R
import com.example.keeptruckin.di.component.DaggerHomeComponent
import com.example.keeptruckin.di.module.HomeModule
import javax.inject.Inject

class HomeActivity : AppCompatActivity() {
    @Inject
    lateinit var homeViewModel: HomeViewModel
    lateinit var binding: com.example.keeptruckin.databinding.ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        setupFragmentComponent()
        initComponents()
    }

    fun initComponents() {
        getSupportActionBar()?.setDisplayShowTitleEnabled(false);
        binding.apply{
            viewModel = homeViewModel
            lifecycleOwner = this@HomeActivity


        }
    }

    fun setupFragmentComponent() {
        DaggerHomeComponent.builder()
            .applicationComponent(AppApplication.getInstance()?.mComponent)
            .homeModule(HomeModule(this))
            .build().inject(this)
    }
}