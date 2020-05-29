package com.example.keeptruckin.di.component

import com.example.keeptruckin.di.module.CitySearchModule
import com.example.keeptruckin.di.scope.ActivityScoped
import com.example.keeptruckin.module.home.citySearch.CitySearchActivity
import dagger.Component

@ActivityScoped
@Component(dependencies = [ApplicationComponent::class], modules = [CitySearchModule::class])
interface CitySearchComponent {
    fun inject(citySearchActivity: CitySearchActivity)
}