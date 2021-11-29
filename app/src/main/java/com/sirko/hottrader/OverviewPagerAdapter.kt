package com.sirko.hottrader

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.viewpager2.adapter.FragmentStateAdapter

class OverviewPagerAdapter(
    manager: FragmentManager,
    lifecycle: Lifecycle,
    private val strings: List<String>,
) : FragmentStateAdapter(manager, lifecycle) {

    override fun getItemCount(): Int = strings.size

    override fun createFragment(position: Int): Fragment =
        OverviewProductFragment.newInstance(strings[position])
}