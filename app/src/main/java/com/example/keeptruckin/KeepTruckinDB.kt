package com.example.keeptruckin

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.keeptruckin.model.CityDetail


@TypeConverters(ObjectTypeConverters::class)
@Database(entities = [CityDetail::class], version = 1)
abstract class KeepTruckinDB : RoomDatabase(){
    abstract val citiesDao : CitiesDao
}