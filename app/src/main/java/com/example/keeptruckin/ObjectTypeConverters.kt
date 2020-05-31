package com.example.keeptruckin

import androidx.room.TypeConverter
import com.example.keeptruckin.model.Links
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ObjectTypeConverters {

    @TypeConverter
    fun convertObjectToString(links : Links): String {
        val gson = Gson()
        return gson.toJson(links)
    }


    @TypeConverter
    fun ConvertStringToObject(links : String): Links? {
        val gson = Gson()
        return gson.fromJson<Links>(links, object : TypeToken<Links>() {
        }.type)
    }
}