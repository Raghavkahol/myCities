package com.example.keeptruckin.di.component

import android.content.Context
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.KeepTruckinDB
import com.example.keeptruckin.di.module.ApplicationModule
import com.example.keeptruckin.di.qualifier.ApplicationContext
import com.example.keeptruckin.di.scope.ApplicationScoped
import com.example.keeptruckin.service.ApiService
import dagger.Component

@ApplicationScoped
@Component(modules = [ApplicationModule::class])
interface ApplicationComponent {
    @get:ApplicationContext
    val context: Context

    val apiService : ApiService

    fun getKeepTruckinDataBase(): KeepTruckinDB

    fun getCitiesDao(): CitiesDao
}