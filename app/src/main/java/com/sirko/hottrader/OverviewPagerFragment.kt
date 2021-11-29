package com.sirko.hottrader

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.viewpager2.widget.ViewPager2

class OverviewPagerFragment : Fragment() {
    private val model: HottraderViewModel
        get() = ViewModelProvider(requireActivity())[HottraderViewModel::class.java]

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? = inflater.inflate(R.layout.fragment_overview_pager, container, false).apply {
        model.overview.observe(viewLifecycleOwner, { overview ->
            findViewById<ViewPager2>(R.id.pager_overview).adapter =
                OverviewPagerAdapter(childFragmentManager, lifecycle, overview)
        })
    }

    companion object {
        @JvmStatic
        fun newInstance() = OverviewPagerFragment()
    }
}