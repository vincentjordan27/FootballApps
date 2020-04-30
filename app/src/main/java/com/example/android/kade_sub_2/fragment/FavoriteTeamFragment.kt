package com.example.android.kade_sub_2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.activity.DetailTeamActivity
import com.example.android.kade_sub_2.adapter.TeamListAdapter
import com.example.android.kade_sub_2.db.database
import com.example.android.kade_sub_2.model.TeamModel
import com.example.android.kade_sub_2.utils.invisible
import kotlinx.android.synthetic.main.fragment_favorite_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.select
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class FavoriteTeamFragment : Fragment() {


    private var favorite: MutableList<TeamModel> = mutableListOf()
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: TeamListAdapter
    private lateinit var progressBar: ProgressBar
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_favorite_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        swipeRefreshLayout = swipe_fav_team
        recyclerView = fav_team_rv
        progressBar = fav_team_prog

        recyclerView.layoutManager = LinearLayoutManager(context)
        adapter = TeamListAdapter(favorite){
            val intent = Intent(context, DetailTeamActivity::class.java)
            intent.putExtra("data",it)
            startActivity(intent)
        }

        recyclerView.adapter = adapter
        showFavorite()
        swipeRefreshLayout.onRefresh {
            showFavorite()
        }
    }

    override fun onResume() {
        super.onResume()
        showFavorite()
    }

    private fun showFavorite(){
        favorite.clear()
        context?.database?.use {
            swipeRefreshLayout.isRefreshing = false
            val result = select(TeamModel.TEAM_FAVORITE)
            val favorites = result.parseList(classParser<TeamModel>())
            favorite.addAll(favorites)
            progressBar.invisible()
            adapter.notifyDataSetChanged()

        }
    }
}
