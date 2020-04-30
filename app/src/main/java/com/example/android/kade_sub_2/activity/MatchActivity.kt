package com.example.android.kade_sub_2.activity

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager.widget.ViewPager
import com.bumptech.glide.Glide
import com.example.android.kade_sub_2.*
import com.example.android.kade_sub_2.adapter.TabAdapter
import com.example.android.kade_sub_2.model.League
import com.google.android.material.tabs.TabLayout
import kotlinx.android.synthetic.main.activity_match.*

class MatchActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_match)

        val data = intent.getParcelableExtra<League>("datas")
        Glide.with(this).load(data?.image).override(100,100).into(img_league)
        desc_league.text = data?.desc


        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        val tabLayout = findViewById<TabLayout>(R.id.tabLayout)
        val viewPager = findViewById<ViewPager>(R.id.viewPager)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        tabLayout?.addTab(tabLayout.newTab().setText(R.string.prev_match_fragment))
        tabLayout?.addTab(tabLayout.newTab().setText(R.string.next_match_fragment))
        tabLayout?.addTab(tabLayout.newTab().setText("Teams"))
        tabLayout?.addTab(tabLayout.newTab().setText("Standing"))

        val adapter = data?.id?.let {
            TabAdapter(
                supportFragmentManager, tabLayout.tabCount, it
            )
        }

        viewPager?.adapter = adapter

        viewPager.addOnPageChangeListener(TabLayout.TabLayoutOnPageChangeListener(tabLayout))
        tabLayout.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener{
            override fun onTabReselected(tab: TabLayout.Tab) {
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab) {
                viewPager.currentItem = tab.position
            }

        })


    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu,menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.search_activity -> {
                startActivity(Intent(this,
                    SearchActivity::class.java))
            }
            android.R.id.home -> {
                startActivity(Intent(this,
                    MainActivity::class.java))
            }
            R.id.favorite_match -> {
                startActivity(Intent(this,
                FavoriteActivity::class.java))
            }
            R.id.search_team -> {
                startActivity(Intent(this,
                SearchTeamActivity::class.java))
            }
        }
        return super.onOptionsItemSelected(item)

    }
}
