package com.example.keeptruckin.module.home.cityDetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.service.ApiService

class CityDetailViewModelFactory(private var apiService: ApiService, private var citiesDao: CitiesDao) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return CityDetailViewModel(apiService, citiesDao) as T
    }
}