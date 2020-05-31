package com.example.keeptruckin.di.module

import com.example.keeptruckin.di.scope.ApplicationScoped
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ContextModule::class, ApiServiceModule::class))
class ApplicationModule {
    //provide dependancies that are required in Application level
}