package com.example.keeptruckin.di.module

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.keeptruckin.di.scope.ActivityScoped
import com.example.keeptruckin.module.home.cityDetail.CityDetailViewModel
import com.example.keeptruckin.module.home.cityDetail.CityDetailViewModelFactory
import com.example.keeptruckin.module.home.citySearch.CitySearchViewModel
import com.example.keeptruckin.module.home.citySearch.CitySearchViewModelFactory
import com.example.keeptruckin.service.ApiService
import dagger.Module
import dagger.Provides

@Module
class CitySearchModule(private val activity: FragmentActivity) {
    @ActivityScoped
    @Provides
    fun provideCitySearchViewModel(citySearchViewModelFactory: CitySearchViewModelFactory): CitySearchViewModel {
        return ViewModelProviders.of(activity, citySearchViewModelFactory).get(CitySearchViewModel::class.java)
    }

    @ActivityScoped
    @Provides
    fun provideCitySearchViewModelFactory(apiService: ApiService): CitySearchViewModelFactory{
        return CitySearchViewModelFactory(apiService);
    }
}