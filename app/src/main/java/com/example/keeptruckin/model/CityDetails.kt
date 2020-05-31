package com.example.keeptruckin.model
import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Entity
data class CityDetail(
    @PrimaryKey
    val geoname_id: Int? = null,
    val _links: Links? = null,
    val name: String? = null,
    val population: Int? = null
)


data class Links(
    @SerializedName("city:admin1_division")
    val province_details: ProvinceDetail? = null,
    @SerializedName("city:country")
    val country_details: CityCountry? = null,
    @SerializedName("city:timezone")
    val city_timezone: CityTimezone? = null,
    val self: SelfData? = null
)


data class ProvinceDetail(
    val name: String? = null
)

data class SelfData(
    val href: String? = null
)


data class CityCountry(
    val name: String? = null
)


data class CityTimezone(
    val name: String? = null
)