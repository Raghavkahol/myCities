package com.example.keeptruckin.module.home.citySearch

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat.startActivity
import androidx.recyclerview.widget.RecyclerView
import com.example.keeptruckin.BaseRecyclerViewAdapter
import com.example.keeptruckin.R
import com.example.keeptruckin.model.CitySearchResult
import com.example.keeptruckin.module.home.cityDetail.CityDetailActivity
import com.example.keeptruckin.module.home.cityDetail.getCityDetailActivity
import kotlinx.android.synthetic.main.city_search_layout.view.*

class CitySearchAdapter(context: Context,  mList: List<CitySearchResult>) :  BaseRecyclerViewAdapter<CitySearchResult>(context, mList){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return CitySearchViewHolder(LayoutInflater.from(context).inflate(R.layout.city_search_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is CitySearchViewHolder)
            holder.bindView(mList?.get(position))

    }

    internal inner class CitySearchViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {
        fun bindView(city: CitySearchResult?) {
            val cityData = city?.matching_full_name?.split(",")?.toTypedArray()
            itemView.setOnClickListener(this)
            itemView.city_name.text = cityData?.get(0)
            itemView.state_name.text = cityData?.get(1)
            itemView.country_name.text = cityData?.get(2)
        }

        override fun onClick(v: View?) {
             val city: CitySearchResult? = mList?.get(adapterPosition)
             context.startActivity(getCityDetailActivity(context, city?._links?.city_item?.href ))
        }
    }
}