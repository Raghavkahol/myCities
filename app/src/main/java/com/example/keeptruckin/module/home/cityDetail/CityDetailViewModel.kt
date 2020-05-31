package com.example.keeptruckin.module.home.cityDetail

import android.text.TextUtils
import androidx.lifecycle.MutableLiveData
import com.example.keeptruckin.AppConstants
import com.example.keeptruckin.BaseViewModel
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.ViewModelLifecycleState
import com.example.keeptruckin.model.CityDetail
import com.example.keeptruckin.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

class CityDetailViewModel(private val apiService: ApiService, private val citiesDao: CitiesDao) : BaseViewModel() {

    var cityName= MutableLiveData<String>().apply{AppConstants.EMPTY_STRING}
    var provinceName= MutableLiveData<String>().apply{AppConstants.EMPTY_STRING}
    var countryValue= MutableLiveData<String>().apply{AppConstants.EMPTY_STRING}
    var timeZone= MutableLiveData<String>().apply{AppConstants.EMPTY_STRING}
    var population= MutableLiveData<Int>().apply{AppConstants.EMPTY_STRING}
    var isInDB = MutableLiveData<Boolean>().apply { false }
    var isDataAvailable = MutableLiveData<Boolean>().apply { false }
    var dataLoading: MutableLiveData<Boolean> = MutableLiveData<Boolean>().apply{false}

    private lateinit var cityDetail : CityDetail

    fun fetchCityDetail(stringExtra: String?) {
        if(!TextUtils.isEmpty(stringExtra)) {
            dataLoading.value = true
            bindDisposable {
                apiService.getCityDetailsByName(stringExtra)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        dataLoading.value = false
                        cityDetail = it
                        cityName.value = it.name
                        it._links?.let{
                            provinceName.value = it.province_details?.name
                            countryValue.value = it.country_details?.name
                            timeZone.value = it.city_timezone?.name
                        }

                        population.value = it.population
                        checkForCityInDB(it.geoname_id)
                    }, {
                        dataLoading.value = false
                        it.printStackTrace()
                    })
            }
        }
    }

    private fun checkForCityInDB(geonameId: Int?) {
        bindDisposable {
            citiesDao.usersCount(cityDetail.geoname_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    if(it>AppConstants.ZERO_INT) {
                        isInDB.value = true
                    }
                    isDataAvailable.value = true
                }, {
                    it.printStackTrace()
                })
        }
    }

    fun updateItem() {
        if(::cityDetail.isInitialized) {
            if (isInDB.value == true) {
                removeCityFromDB()
            } else {
                addCityInDB()
            }
        }
    }

    private fun addCityInDB() {
        bindDisposable {
            citiesDao.insertCity(cityDetail)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    closeCityDetailActivity()
                }, {
                    it.printStackTrace()
                })
        }
    }

    private fun removeCityFromDB() {
        bindDisposable {
            citiesDao.deleteCity(cityDetail.geoname_id)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    closeCityDetailActivity()
                }, {
                    it.printStackTrace()
                })
        }
    }

    fun closeCityDetailActivity() {
        lifecycleState.onNext(ViewModelLifecycleState.FinishedWithResult)
    }


}