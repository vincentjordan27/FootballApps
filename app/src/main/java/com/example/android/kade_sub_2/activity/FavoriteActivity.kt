package com.example.android.kade_sub_2.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.example.android.kade_sub_2.MainActivity
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.adapter.TabFavAdapter
import com.google.android.material.tabs.TabLayout

class FavoriteActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        val toolbar = findViewById<Toolbar>(R.id.fav_toolbar)
        toolbar.title = "Favorite"
        setSupportActionBar(toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        val tabLayout = findViewById<TabLayout>(R.id.fav_tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.fav_viewPager)

        tabLayout?.addTab(tabLayout.newTab().setText("Match"))
        tabLayout?.addTab(tabLayout.newTab().setText("Team"))

        val adapter = TabFavAdapter(supportFragmentManager, tabLayout.tabCount)

        viewPager.adapter = adapter
        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab?) {

            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {

            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

        })
    }



    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                startActivity(Intent(this,
                    MainActivity::class.java))
            }

        }
        return super.onOptionsItemSelected(item)

    }
}
