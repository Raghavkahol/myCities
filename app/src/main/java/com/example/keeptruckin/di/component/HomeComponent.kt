package com.example.keeptruckin.di.component

import com.example.keeptruckin.di.module.HomeModule
import com.example.keeptruckin.di.scope.ActivityScoped
import com.example.keeptruckin.module.home.HomeActivity
import dagger.Component

@ActivityScoped
@Component(dependencies = [ApplicationComponent::class], modules = [HomeModule::class])
interface HomeComponent {
    fun inject(homeActivity: HomeActivity)
}