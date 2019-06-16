package com.example.stationviewer.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.databinding.DataBindingUtil
import com.example.stationviewer.R
import com.example.stationviewer.data.StationOutputData
import com.example.stationviewer.databinding.ListItemBinding

class StationListViewAdapter(context: Context, list: MutableList<StationOutputData>): ArrayAdapter<StationOutputData>(context, 0, list) {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        return if (convertView == null) {
            val inflater = LayoutInflater.from(context)
            val binding = DataBindingUtil.inflate<ListItemBinding>(inflater, R.layout.list_item, parent, false)
            binding.root.tag = binding

            binding.station = getItem(position)
            binding.root
        } else {
            val bindingFromTag = convertView.tag as ListItemBinding

            bindingFromTag.station = getItem(position)
            bindingFromTag.root
        }
    }
}