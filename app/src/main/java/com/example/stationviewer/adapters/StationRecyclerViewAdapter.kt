package com.example.stationviewer.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.stationviewer.R
import com.example.stationviewer.data.StationData
import com.example.stationviewer.holders.StationViewHolder

class StationRecyclerViewAdapter(private val list: List<StationData>): RecyclerView.Adapter<StationViewHolder>() {

    override fun onBindViewHolder(holder: StationViewHolder, position: Int) {
        holder.textName.text = list[position].name
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StationViewHolder {
        val inflate = LayoutInflater.from(parent.context).inflate(R.layout.lists_item, parent, false)
        return StationViewHolder(inflate)
    }

    override fun getItemCount(): Int {
        return  list.size
    }
}