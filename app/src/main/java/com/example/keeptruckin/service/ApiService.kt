package com.example.keeptruckin.service


import com.example.keeptruckin.model.Cities
import com.example.keeptruckin.model.CityDetail
import retrofit2.http.GET
import retrofit2.http.Query
import io.reactivex.Single
import retrofit2.http.Path
import retrofit2.http.Url


interface ApiService {

    @GET("cities/")
    fun getCityListSearchedByName(@Query("search") cityName: String?): Single<Cities>

    @GET
    fun getCityDetailsByName(@Url stringExtra: String?): Single<CityDetail>

    @GET("locations/{lat},{long}/")
    fun getCityListSearchedByLocation(@Path("lat") latitude: Double,@Path("long") longitude: Double): Single<Cities>

}