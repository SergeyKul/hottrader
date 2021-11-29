package com.sirko.hottrader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.ListView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider

class DescriptionProductFragment : Fragment() {
    private val model: HottraderViewModel
        get() = ViewModelProvider(requireActivity())[HottraderViewModel::class.java]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_description_product, container, false).apply {
        model.description.observe(viewLifecycleOwner, { description ->
            findViewById<ListView>(R.id.listview_product_description).adapter =
                ArrayAdapter(context, android.R.layout.simple_list_item_1, description)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = DescriptionProductFragment()
    }
}