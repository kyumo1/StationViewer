package com.example.stationviewer.ui.main

import android.app.Application
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ListView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.stationviewer.MainActivity
import com.example.stationviewer.MapsActivity
import com.example.stationviewer.R
import com.example.stationviewer.adapters.StationListViewAdapter
import com.example.stationviewer.data.StationOutputData
import com.example.stationviewer.di.component.DaggerStationsFragmentComponent
import com.example.stationviewer.di.module.Module
import com.example.stationviewer.repositories.StationsRepository
import com.example.stationviewer.usecases.GetStationsUseCase
import com.example.stationviewer.viewmodels.StationsViewModel
import dagger.android.support.DaggerFragment
import javax.inject.Inject

/**
 * A placeholder fragment containing a simple view.
 */
class StationsFragment : Fragment() {

    @Inject lateinit var viewModel: StationsViewModel

    private val component = DaggerStationsFragmentComponent.builder().build()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        component.inject(this)
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
        val list = root.findViewById<ListView>(R.id.list)
        list.adapter = StationListViewAdapter(requireContext(), mutableListOf())
        viewModel.getStations(arguments?.getInt(ARG_LINE_NUMBER) ?: 11302).observe(this, Observer {
            (list.adapter as StationListViewAdapter).addAll(it)
        })
        list.setOnItemClickListener { _, _, position, _ ->
            val intent = Intent(requireContext(), MapsActivity::class.java)
            val data = (list.adapter.getItem(position) as StationOutputData)
            intent.putExtra(MapsActivity.EXTRA_LATITUDE, data.latitude)
            intent.putExtra(MapsActivity.EXTRA_LONGITUDE, data.longitude)
            startActivity(intent)
        }

        return root
    }

    companion object {
        private const val ARG_LINE_NUMBER = "line_number"
        @JvmStatic
        fun newInstance(lineNumber: Int): StationsFragment {
            return StationsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_LINE_NUMBER, lineNumber)
                }
            }
        }
    }
}