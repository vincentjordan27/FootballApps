package com.example.android.kade_sub_2.fragment

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout
import com.example.android.kade_sub_2.adapter.PrevMatchAdapter
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.activity.DetailActivity
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.presenter.MatchPresenter
import com.example.android.kade_sub_2.view.MatchView
import com.example.android.kade_sub_2.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_previous_match.*
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class PreviousMatchFragment() : Fragment(),
    MatchView {


    private lateinit var recycleView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var match: MutableList<MatchModel> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: PrevMatchAdapter
    private lateinit var leagueId: String

    constructor(id: String) : this() {
        leagueId = id
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_previous_match, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val request = ApiRepository()
        val gson = Gson()
        presenter = MatchPresenter(
            this,
            request,
            gson
        )


        swipeRefreshLayout = swipe_prev_match
        recycleView = prev_match_rv

        adapter =
            PrevMatchAdapter(match) {
                val intent = Intent(
                    context,
                    DetailActivity::class.java
                )
                intent.putExtra("data", it)
                startActivity(intent)
            }


        recycleView.layoutManager = LinearLayoutManager(context)
        recycleView.adapter = adapter
        Log.i("debug",leagueId)
        Log.i("debug gson",gson.toString())
        presenter.getMatchList(leagueId)
        swipeRefreshLayout.onRefresh {
            presenter.getMatchList(leagueId)
        }

    }

    override fun showLoading() {
        prev_match_prog.visible()
    }

    override fun hideLoading() {
        prev_match_prog.invisible()
    }

    override fun showMatchList(data: List<MatchModel>) {
        swipeRefreshLayout.isRefreshing = false
        match.clear()
        match.addAll(data)
        for(i in data){
            Log.i("print","Nama Id " + i.idEvent)
        }
        adapter.notifyDataSetChanged()
    }
}
