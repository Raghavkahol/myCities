package com.example.keeptruckin.di.module

import android.content.Context
import androidx.room.Room
import com.example.keeptruckin.CitiesDao
import com.example.keeptruckin.KeepTruckinDB
import com.example.keeptruckin.di.qualifier.ApplicationContext
import com.example.keeptruckin.di.scope.ApplicationScoped
import dagger.Module
import dagger.Provides

@Module(includes = arrayOf(ContextModule::class, ApiServiceModule::class))
class ApplicationModule {
    @Provides
    @ApplicationScoped
    internal fun provideDataBase(@ApplicationContext context: Context): KeepTruckinDB {
        return Room.databaseBuilder(context, KeepTruckinDB::class.java, "keeptruckin.db")
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @ApplicationScoped
    internal fun provideCitiesDao(db: KeepTruckinDB): CitiesDao {
        return db.citiesDao
    }
}