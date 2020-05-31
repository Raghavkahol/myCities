package com.example.keeptruckin.module.home.cityDetail

import android.content.Context
import android.content.Intent
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
import com.example.keeptruckin.module.home.citySearch.CitySearchActivity
import kotlinx.android.synthetic.main.activity_city_search.*
import kotlinx.android.synthetic.main.city_search_layout.view.*
import javax.inject.Inject

fun getCityDetailActivity(context: Context, cityName : String?): Intent {
    val intent  = Intent(context, CityDetailActivity::class.java)
    intent.putExtra("HREF", cityName)
    context.startActivity(intent)
    return intent
}

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
        cityDetailViewModel.fetchCityDetail(intent.getStringExtra("HREF"))
    }

    fun initComponents() {
        cityDetailViewModel.apply {
            binding.viewModel = this
            bindViewModel(this)
        }
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