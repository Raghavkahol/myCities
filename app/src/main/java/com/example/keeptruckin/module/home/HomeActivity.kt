package com.example.keeptruckin.module.home

import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeptruckin.*
import com.example.keeptruckin.di.component.DaggerHomeComponent
import com.example.keeptruckin.di.module.HomeModule
import com.example.keeptruckin.model.CityDetail
import com.example.keeptruckin.module.home.citySearch.getCitySearchIntent
import javax.inject.Inject

class HomeActivity : BaseViewModelActivity() {
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
        homeViewModel.apply {
            binding.viewModel = this
            bindViewModel(this)
        }
        binding.apply{
            viewModel = homeViewModel
            lifecycleOwner = this@HomeActivity
            recyclerView.apply {
                layoutManager = LinearLayoutManager(context)
                adapter = HomeAdapter(context, ArrayList<CityDetail>())
                itemAnimator = DefaultItemAnimator()
                addItemDecoration(DividerItemDecoration(this@HomeActivity, LinearLayoutManager.VERTICAL))
            }

        }
    }

    override fun onResume() {
        super.onResume()
        homeViewModel.getCardsData()
    }

    override fun setupFragmentComponent() {
        DaggerHomeComponent.builder()
            .applicationComponent(AppApplication.getInstance()?.mComponent)
            .homeModule(HomeModule(this))
            .build().inject(this)
    }

    override fun onViewModelStartWithRequest(state: ViewModelLifecycleState.StartWithRequest) {
        when (state.request) {
            AppConstants.ONE_INT -> {
                startActivity(getCitySearchIntent(this))
            }
        }
    }
}
