package com.example.keeptruckin.module.home.cityDetail

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.keeptruckin.BaseViewModel
import com.example.keeptruckin.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityDetailViewModel(private val apiService: ApiService) : BaseViewModel() {

    var cityName= MutableLiveData<String>().apply{""}
    var provinceName= MutableLiveData<String>().apply{""}
    var countryValue= MutableLiveData<String>().apply{""}
    var timeZone= MutableLiveData<String>().apply{""}
    var population= MutableLiveData<Int>().apply{""}
    var isInDB = MutableLiveData<Boolean>().apply { false }

    fun fetchCityDetail(stringExtra: String?) {
        if(!TextUtils.isEmpty(stringExtra)) {
            bindDisposable {
                apiService.getCityDetailsByName(stringExtra)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        cityName.value = it.name
                        provinceName.value = it._links?.province_details?.name
                        countryValue.value = it._links?.country_details?.name
                        timeZone.value = it._links?.city_timezone?.name
                        population.value = it.population
                    }, {
                        it.printStackTrace()
                    })
            }
        }
    }
}