package com.example.android.kade_sub_2.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout

import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.adapter.StandingAdapter
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.StandingModel
import com.example.android.kade_sub_2.presenter.StandingPresenter
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.utils.visible
import com.example.android.kade_sub_2.view.StandingView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_standing.*
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class StandingFragment() : Fragment(), StandingView {

    private lateinit var recycleView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var teams: MutableList<StandingModel> = mutableListOf()
    private lateinit var presenter: StandingPresenter
    private lateinit var adapter: StandingAdapter
    private lateinit var leagueId: String

    constructor(id: String) : this() {
        leagueId = id
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_standing, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = StandingPresenter(this,request,gson)

        swipeRefreshLayout = swipe_standing
        recycleView = rv_standing

        adapter = StandingAdapter(teams)
        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter
        presenter.getStandingTable(leagueId)
        swipeRefreshLayout.onRefresh {
            presenter.getStandingTable(leagueId)
        }
    }

    override fun showLoading() {
        standing_prog.visible()
    }

    override fun getStanding(data: List<StandingModel>) {
        swipeRefreshLayout.isRefreshing = false
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun hideLoading() {
        standing_prog.invisible()
    }

}
