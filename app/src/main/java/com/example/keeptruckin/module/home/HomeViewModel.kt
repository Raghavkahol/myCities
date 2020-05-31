package com.example.keeptruckin.module.home

import androidx.databinding.ObservableArrayList
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.keeptruckin.AppConstants
import com.example.keeptruckin.BaseViewModel
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.ViewModelLifecycleState
import com.example.keeptruckin.model.CityDetail
import com.example.keeptruckin.model.CitySearchResult
import com.example.keeptruckin.service.ApiService
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.launch


class HomeViewModel(private val citiesDao: CitiesDao) : BaseViewModel() {


    var cities = ObservableArrayList<CityDetail>()
    var isDataUnavalable = MutableLiveData<Boolean>().apply { false }

    fun getCardsData() {
        bindDisposable {
                citiesDao.getCities()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe({
                        with(cities){
                            clear()
                            addAll(it)
                        }
                        if(it.size == AppConstants.ZERO_INT)
                            isDataUnavalable.value = true
                        else
                            isDataUnavalable.value = false
                    }, {
                        it.printStackTrace()
                    })
        }
    }

    fun openCitySearchActivity() {
        lifecycleState.onNext(ViewModelLifecycleState.StartWithRequest(AppConstants.ONE_INT))
    }
}