package com.example.keeptruckin.di.module

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.di.scope.ActivityScoped
import com.example.keeptruckin.module.home.cityDetail.CityDetailViewModel
import com.example.keeptruckin.module.home.cityDetail.CityDetailViewModelFactory
import com.example.keeptruckin.service.ApiService
import dagger.Module
import dagger.Provides

@Module
class CityDetailModule(private val activity: FragmentActivity) {
    @ActivityScoped
    @Provides
    fun provideCityDetailViewModel(cityDetailViewModelFactory: CityDetailViewModelFactory): CityDetailViewModel {
        return ViewModelProviders.of(activity, cityDetailViewModelFactory).get(CityDetailViewModel::class.java)
    }

    @ActivityScoped
    @Provides
    fun provideCityDetailViewModelFactory(apiService: ApiService, citiesDao: CitiesDao): CityDetailViewModelFactory {
        return CityDetailViewModelFactory(apiService, citiesDao);
    }
}