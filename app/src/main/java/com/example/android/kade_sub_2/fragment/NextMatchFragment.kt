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
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.activity.DetailActivity
import com.example.android.kade_sub_2.adapter.NextMatchAdapter
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.presenter.MatchPresenter
import com.example.android.kade_sub_2.view.MatchView
import com.example.android.kade_sub_2.utils.visible
import com.google.gson.Gson
import kotlinx.android.synthetic.main.fragment_next_match.*
import org.jetbrains.anko.support.v4.onRefresh

/**
 * A simple [Fragment] subclass.
 */
class NextMatchFragment() : Fragment(), MatchView {

    private lateinit var recycleView: RecyclerView
    private lateinit var swipeRefreshLayout: SwipeRefreshLayout
    private var match: MutableList<MatchModel> = mutableListOf()
    private lateinit var presenter: MatchPresenter
    private lateinit var adapter: NextMatchAdapter
    private lateinit var leagueId: String

    constructor(id: String) : this() {
        leagueId = id
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_next_match, container, false)
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


        swipeRefreshLayout = swipe_next_match
        recycleView = next_match_rv

        adapter =
            NextMatchAdapter(match) {
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
        presenter.getMatchNextList(leagueId)
        swipeRefreshLayout.onRefresh {
            presenter.getMatchNextList(leagueId)
        }

    }

    override fun showLoading() {
        next_match_prog.visible()
    }

    override fun hideLoading() {
        next_match_prog.invisible()
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

    override fun onDetach() {
        super.onDetach()
        presenter.onDetach()
    }
}
