package com.example.keeptruckin

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.keeptruckin.model.CityDetail
import io.reactivex.Completable
import io.reactivex.Single

@Dao
interface CitiesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertCity(city: CityDetail) : Completable

    @Query("DELETE FROM CityDetail where CityDetail.geoname_id = :id")
    fun deleteCity(id: Int?) : Completable

    @Query("SELECT * FROM CityDetail")
    fun getCities(): Single<List<CityDetail>>

    @Query("SELECT COUNT(*) from CityDetail where CityDetail.geoname_id = :id")
    fun usersCount(id: Int?) : Single<Int>

}