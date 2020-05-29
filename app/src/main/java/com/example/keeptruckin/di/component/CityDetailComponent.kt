package com.example.keeptruckin.di.component

import com.example.keeptruckin.di.module.CityDetailModule
import com.example.keeptruckin.di.scope.ActivityScoped
import com.example.keeptruckin.module.home.cityDetail.CityDetailActivity
import dagger.Component

@ActivityScoped
@Component(dependencies = [ApplicationComponent::class], modules = [CityDetailModule::class])
interface CityDetailComponent {
    fun inject(cityDetailActivity: CityDetailActivity)
}