package com.example.keeptruckin.module.home.cityDetail

import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.keeptruckin.AppApplication
import com.example.keeptruckin.BaseViewModelActivity
import com.example.keeptruckin.R
import com.example.keeptruckin.di.component.DaggerCityDetailComponent
import com.example.keeptruckin.di.module.CityDetailModule
import kotlinx.android.synthetic.main.activity_city_search.*
import javax.inject.Inject

class CityDetailActivity : BaseViewModelActivity() {
    @Inject
    lateinit var cityDetailViewModel: CityDetailViewModel
    lateinit var binding: com.example.keeptruckin.databinding.ActivityCityDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_city_details)
        setupFragmentComponent()
        initComponents()
        setToolbar()
    }

    fun initComponents() {
       binding.apply {
           viewModel = cityDetailViewModel
           lifecycleOwner = this@CityDetailActivity
       }
    }

    override fun setupFragmentComponent() {
        DaggerCityDetailComponent.builder()
            .applicationComponent(AppApplication.getInstance()?.mComponent)
            .cityDetailModule(CityDetailModule(this))
            .build().inject(this)
    }
}