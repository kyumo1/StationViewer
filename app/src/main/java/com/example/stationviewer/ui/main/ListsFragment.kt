package com.example.stationviewer.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.stationviewer.R
import com.example.stationviewer.adapters.StationRecyclerViewAdapter
import com.example.stationviewer.data.StationData

/**
 * A placeholder fragment containing a simple view.
 */
class ListsFragment : Fragment() {

    private lateinit var pageViewModel: PageViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        pageViewModel = ViewModelProviders.of(this).get(PageViewModel::class.java).apply {
            setIndex(arguments?.getInt(ARG_SECTION_NUMBER) ?: 1)
        }
    }

    override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val root = inflater.inflate(R.layout.fragment_main, container, false)
//        val textView: TextView = root.findViewById(R.id.section_label)
//        pageViewModel.text.observe(this, Observer<String> {
//            textView.text = it
//        })
//        val listView: ListView = root.findViewById(R.id.list)
//        val items = arrayOf("新宿", "渋谷")
//        val arrayAdapter = ArrayAdapter(this.requireContext(), android.R.layout.simple_expandable_list_item_1, items)
//
//        listView.adapter = arrayAdapter
        val recyclerView: RecyclerView = root.findViewById(R.id.recycler_lists)
        val adapter = StationRecyclerViewAdapter(createList())
        val llm = LinearLayoutManager(requireContext())
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = llm
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(DividerItemDecoration(requireContext(), DividerItemDecoration.VERTICAL))

        return root
    }

    private fun createList(): List<StationData> {
        return listOf(StationData("池袋"), StationData("渋谷"))
    }

    companion object {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private const val ARG_SECTION_NUMBER = "section_number"

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        @JvmStatic
        fun newInstance(sectionNumber: Int): ListsFragment {
            return ListsFragment().apply {
                arguments = Bundle().apply {
                    putInt(ARG_SECTION_NUMBER, sectionNumber)
                }
            }
        }
    }
}