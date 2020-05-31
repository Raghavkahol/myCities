package com.example.keeptruckin.module.home.citySearch

import android.text.TextUtils
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.example.keeptruckin.BaseViewModel
import com.example.keeptruckin.model.CitySearchResult
import com.example.keeptruckin.service.ApiService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CitySearchViewModel(private val apiService: ApiService) : BaseViewModel() {

    var cityName= MutableLiveData<String>().apply { "" }
    var cities  = ObservableArrayList<CitySearchResult>()

    var isVisible : MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply { false }

    fun fetchCityList() {
        if(!TextUtils.isEmpty(cityName.value)) {
            bindDisposable {
                apiService.getCityListSearchedByName(cityName.value)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                       it._embedded?.city?.let{
                           isVisible.value = true
                           cities.addAll(it)
                       }
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }


}