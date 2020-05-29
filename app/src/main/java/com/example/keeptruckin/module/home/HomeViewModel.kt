package com.example.keeptruckin.module.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keeptruckin.service.ApiService
import kotlinx.coroutines.launch


class HomeViewModel(private val apiService: ApiService) : ViewModel() {

    private val _dataLoading = MutableLiveData<Boolean>()
    val dataLoading: LiveData<Boolean> = _dataLoading

    init {
        getCardsData()
    }

    private fun getCardsData() {

    }
}