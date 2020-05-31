package com.example.keeptruckin.module.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.service.ApiService

class HomeViewModelFactory(private var citiesDao: CitiesDao) : ViewModelProvider.NewInstanceFactory() {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return HomeViewModel(citiesDao) as T
    }
}