package com.example.keeptruckin.model

import com.google.gson.annotations.SerializedName

data class CityDetail(
    val _links: Links? = null,
    val full_name: String? = null,
    val geoname_id: Int? = null,
    val location: Location? = null,
    val name: String? = null,
    val population: Int? = null
)

data class Links(
    @SerializedName("city:admin1_division")
    val province_details: ProvinceDetail? = null,
    @SerializedName("city:country")
    val country_details: CityCountry? = null,
    @SerializedName("city:timezone")
    val city_timezone: CityTimezone? = null
)

data class Location(
    val geohash: String? = null,
    val latlon: Latlon? = null
)

data class ProvinceDetail(
    val href: String? = null,
    val name: String? = null
)

data class CityCountry(
    val href: String? = null,
    val name: String? = null
)

data class CityTimezone(
    val href: String? = null,
    val name: String? = null
)

data class Latlon(
    val latitude: Double? = null,
    val longitude: Double? = null
)