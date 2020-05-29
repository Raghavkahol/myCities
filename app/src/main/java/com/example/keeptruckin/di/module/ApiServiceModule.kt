package com.example.keeptruckin.di.module

import com.example.keeptruckin.di.qualifier.ApiServiceQualifier
import com.example.keeptruckin.di.scope.ApplicationScoped
import com.example.keeptruckin.service.ApiService
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module(includes = arrayOf(NetworkModule::class))
class ApiServiceModule {
    fun getBaseUrl(): String {
        return "https://api.teleport.org/api/"
    }

    @Provides
    @ApplicationScoped
    fun crsApiService(@ApiServiceQualifier retrofit: Retrofit): ApiService {
        return retrofit.create<ApiService>(ApiService::class.java)
    }

    @Provides
    @ApplicationScoped
    @ApiServiceQualifier
    fun provideRetrofit(httpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(getBaseUrl())
            .client(httpClient)
            .addConverterFactory(GsonConverterFactory.create()).build()
    }
}