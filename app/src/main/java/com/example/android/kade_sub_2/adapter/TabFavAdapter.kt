package com.example.android.kade_sub_2.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.android.kade_sub_2.fragment.FavoriteMatchFragment
import com.example.android.kade_sub_2.fragment.FavoriteTeamFragment

class TabFavAdapter(
    fm: FragmentManager,
    private var totalTabs: Int
)
    : FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> {
                FavoriteMatchFragment()
            }
            else -> {
                FavoriteTeamFragment()
            }

        }
    }

    override fun getCount(): Int {
        return totalTabs
    }

}