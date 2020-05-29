package com.example.keeptruckin.module.home

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeptruckin.AppApplication
import com.example.keeptruckin.R
import com.example.keeptruckin.di.component.DaggerHomeComponent
import com.example.keeptruckin.di.module.HomeModule
import kotlinx.android.synthetic.main.activity_home.*
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
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = HomeAdapter(context, ArrayList<Any>())
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(DividerItemDecoration(this@HomeActivity, LinearLayoutManager.VERTICAL))
            }

        }
    }

    fun setupFragmentComponent() {
        DaggerHomeComponent.builder()
            .applicationComponent(AppApplication.getInstance()?.mComponent)
            .homeModule(HomeModule(this))
            .build().inject(this)
    }
}