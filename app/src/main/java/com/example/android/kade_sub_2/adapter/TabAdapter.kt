package com.example.android.kade_sub_2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android.kade_sub_2.fragment.NextMatchFragment
import com.example.android.kade_sub_2.fragment.PreviousMatchFragment
import com.example.android.kade_sub_2.fragment.StandingFragment
import com.example.android.kade_sub_2.fragment.TeamFragment

class TabAdapter(
    fm: FragmentManager,
    private var totalTabs: Int,
    var id: String
)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                PreviousMatchFragment(
                    id
                )
            }
            1 -> {
                NextMatchFragment(
                    id
                )
            }
            2 -> {
                TeamFragment(id)
            }
            else -> {
                StandingFragment(id)
            }

        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}