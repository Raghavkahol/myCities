package com.example.keeptruckin.module.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keeptruckin.BaseRecyclerViewAdapter
import com.example.keeptruckin.R
import com.example.keeptruckin.model.CityDetail
import com.example.keeptruckin.module.home.cityDetail.getCityDetailActivity
import kotlinx.android.synthetic.main.city_card_layout.view.*

class HomeAdapter( context: Context, mList: List<CityDetail>) : BaseRecyclerViewAdapter<CityDetail>(context, mList) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.city_card_layout, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeItemViewHolder)
            holder.bindView(mList?.get(position))
    }

    internal inner class HomeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView), View.OnClickListener {

        override fun onClick(view: View?) {
            val city: CityDetail? = mList?.get(adapterPosition)
            context.startActivity(getCityDetailActivity(context, city?._links?.self?.href))
        }

        fun bindView(cityDetail: CityDetail?) {
            itemView.setOnClickListener(this)
            itemView.city_name.text = cityDetail?.name
        }


    }
}
