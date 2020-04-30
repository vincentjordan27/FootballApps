package com.example.android.kade_sub_2

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kade_sub_2.activity.MatchActivity
import com.example.android.kade_sub_2.adapter.LeagueAdapter
import com.example.android.kade_sub_2.model.League
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.startActivity

class MainActivity : AppCompatActivity() {

    private var items: MutableList<League> = mutableListOf()
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: LeagueAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar = findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)


        initData()
        adapter = LeagueAdapter(items) {
            startActivity<MatchActivity>("datas" to it)
        }

        recyclerView = rv_main
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

    }

    private fun initData() {
        val name = resources.getStringArray(R.array.league_name)
        val image = resources.obtainTypedArray(R.array.league_image)
        val desc = resources.getStringArray(R.array.league_desc)
        val id = resources.getStringArray(R.array.id_league)

        for (i in name.indices) {
            items.add(
                League(
                    name[i],
                    desc[i],
                    image.getResourceId(i, 0),
                    id[i]
                )
            )
        }
        image.recycle()
    }
}
