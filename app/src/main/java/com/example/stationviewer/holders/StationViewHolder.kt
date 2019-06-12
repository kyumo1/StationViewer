package com.example.stationviewer.holders

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.stationviewer.R

class StationViewHolder(itemView : View): RecyclerView.ViewHolder(itemView) {
    val textName: TextView = itemView.findViewById(R.id.text)
}