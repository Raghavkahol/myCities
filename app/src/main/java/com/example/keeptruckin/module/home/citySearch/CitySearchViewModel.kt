package com.example.keeptruckin.module.home.citySearch

import android.text.TextUtils
import androidx.databinding.ObservableArrayList
import androidx.lifecycle.MutableLiveData
import com.example.keeptruckin.AppConstants
import com.example.keeptruckin.BaseViewModel
import com.example.keeptruckin.model.Cities
import com.example.keeptruckin.model.CitySearchResult
import com.example.keeptruckin.service.ApiService

import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CitySearchViewModel(private val apiService: ApiService) : BaseViewModel() {

    var cityName= MutableLiveData<String>().apply { AppConstants.EMPTY_STRING }
    var cities  = ObservableArrayList<CitySearchResult>()
    var isDataUnavalable = MutableLiveData<Boolean>().apply { false }

    var dataLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply{ false }

    fun fetchCityList() {
        if(!TextUtils.isEmpty(cityName.value)) {
            dataLoading.value = true
            bindDisposable {
                apiService.getCityListSearchedByName(cityName.value)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        updateData(it)
                    }, {
                        dataLoading.value = false
                        it.printStackTrace()
                    })
            }
        }
    }

    fun fetchCityListByLocation( latitude : Double, longitude : Double) {
            dataLoading.value = true
            bindDisposable {
                apiService.getCityListSearchedByLocation(latitude, longitude)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        updateData(it)
                    }, {
                        dataLoading.value = false
                        it.printStackTrace()
                    })
            }
    }

    fun updateData(city : Cities) {
        dataLoading.value = false
        cities.clear()
        city._embedded?.city?.let{
                cities.addAll(it)
        }
        isDataUnavalable.value = !(city._embedded?.city != null && city._embedded.city.size != AppConstants.ZERO_INT)
    }
}