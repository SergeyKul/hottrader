package com.sirko.hottrader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomnavigation.BottomNavigationView

class ProductFragment : Fragment() {
    private val model: HottraderViewModel
        get() = ViewModelProvider(requireActivity())[HottraderViewModel::class.java]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_product, container, false).apply {
        findViewById<BottomNavigationView>(R.id.bottom_nav).let {
            it.setOnItemSelectedListener { item ->
                model.itemId = item.itemId
                when (item.itemId) {
                    R.id.bottombaritem_overview -> switchFragment(OverviewPagerFragment.newInstance())
                    R.id.bottombaritem_description -> switchFragment(DescriptionProductFragment.newInstance())
                }
                true
            }
            it.selectedItemId = model.itemId
        }
    }

    private fun switchFragment(fragment: Fragment) {
        parentFragmentManager.beginTransaction()
            .replace(R.id.frame_fragmentholder, fragment)
            .commit()
    }

    companion object {
        @JvmStatic
        fun newInstance() = ProductFragment()
    }
}