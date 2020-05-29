package com.example.keeptruckin.di.module

import androidx.fragment.app.FragmentActivity
import androidx.lifecycle.ViewModelProviders
import com.example.keeptruckin.di.scope.ActivityScoped
import com.example.keeptruckin.module.home.HomeViewModel
import com.example.keeptruckin.module.home.HomeViewModelFactory
import com.example.keeptruckin.service.ApiService
import dagger.Module
import dagger.Provides

@Module
class HomeModule(private val activity: FragmentActivity) {
    @ActivityScoped
    @Provides
    fun provideHomeViewModel(homeViewModelFactory: HomeViewModelFactory): HomeViewModel {
        return ViewModelProviders.of(activity, homeViewModelFactory).get(HomeViewModel::class.java)
    }

    @ActivityScoped
    @Provides
    fun provideHomeViewModelFactory(apiService: ApiService): HomeViewModelFactory{
        return HomeViewModelFactory(apiService);
    }
}