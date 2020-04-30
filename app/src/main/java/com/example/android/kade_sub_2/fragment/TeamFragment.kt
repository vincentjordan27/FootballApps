package com.example.android.kade_sub_2.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.activity.DetailTeamActivity
import com.example.android.kade_sub_2.adapter.TeamListAdapter
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.TeamModel
import com.example.android.kade_sub_2.presenter.TeamListPresenter
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.utils.visible
import com.example.android.kade_sub_2.view.TeamView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_team.*
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class TeamFragment() : Fragment(), TeamView {

    private lateinit var recycleView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var team: MutableList<TeamModel> = mutableListOf()
    private lateinit var listPresenter: TeamListPresenter
    private lateinit var adapter: TeamListAdapter
    private lateinit var leagueId: String

    constructor(id: String) : this() {
        leagueId = id
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_team, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        listPresenter = TeamListPresenter(this,request,gson)

        swipeRefreshLayout = swipe_team_list
        recycleView = team_rv

        adapter = TeamListAdapter(team){
            val intent =Intent(context, DetailTeamActivity::class.java)
            intent.putExtra("data",it)
            startActivity(intent)

        }

        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter
        listPresenter.getTeamList(leagueId)
        swipeRefreshLayout.onRefresh {
            listPresenter.getTeamList(leagueId)
        }
    }

    override fun showLoading() {
        team_prog.visible()
    }

    override fun getTeamList(data: List<TeamModel>) {
        swipeRefreshLayout.isRefreshing = false
        team.clear()
        team.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        team_prog.invisible()
    }

}
