package com.example.keeptruckin.module.home

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.keeptruckin.R

class HomeAdapter(val context: Context, var mList: List<Any>) : RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return HomeItemViewHolder(LayoutInflater.from(context).inflate(R.layout.city_card_layout, parent, false))
    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        if (holder is HomeItemViewHolder)
            holder.bindView()
    }

    internal inner class HomeItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bindView() {

        }

    }
}
