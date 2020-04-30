package com.example.android.kade_sub_2.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.adapter.TeamSearchAdapter
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.model.TeamMatchData
import com.example.android.kade_sub_2.model.TeamModel
import com.example.android.kade_sub_2.presenter.TeamSearchPresenter
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.utils.visible
import com.example.android.kade_sub_2.view.TeamSearchView
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_team_search.*

class SearchTeamActivity : AppCompatActivity() , TeamSearchView{

    private lateinit var presenter: TeamSearchPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: TeamSearchAdapter
    private var teams: MutableList<TeamMatchData> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_team_search)

        val toobar = search_team_toolbar
        setSupportActionBar(toobar)
        supportActionBar?.title = "Search Team"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        adapter = TeamSearchAdapter(teams){
            val team = TeamModel(it.idTeam,it.teamName,it.strLeague,it.teamBadge)
            val intent = Intent(this,DetailTeamActivity::class.java)
            intent.putExtra("data", team)
            startActivity(intent)
        }

        progressBar = pg_search_team
        recyclerView = rv_search_team
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val apiRepository = ApiRepository()
        val gson = GsonBuilder().serializeNulls().create()
        presenter = TeamSearchPresenter(
            this,
            apiRepository,
            gson
        )
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.search_menu,menu)

        val manager = getSystemService(Context.SEARCH_SERVICE) as SearchManager
        val searchItem = menu?.findItem(R.id.search)
        val searchView = searchItem?.actionView as android.widget.SearchView

        searchView.setSearchableInfo(manager.getSearchableInfo(componentName))

        searchView.setOnQueryTextListener(object  : android.widget.SearchView.OnQueryTextListener{
            override fun onQueryTextSubmit(query: String?): Boolean {
                recyclerView.invisible()
                progressBar.visible()
                presenter.getTeamSearch(query.toString())
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })



        return super.onCreateOptionsMenu(menu)

    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progressBar.visible()
    }

    override fun hideLoading() {
        progressBar.invisible()
    }

    override fun getTeamSearch(data: List<TeamMatchData>) {
        recyclerView.visible()
        search_team_not_found.invisible()
        progressBar.invisible()
        teams.clear()
        teams.addAll(data)
        adapter.notifyDataSetChanged()
    }

    override fun empty() {
        recyclerView.invisible()
        search_team_not_found.visible()
        progressBar.invisible()
        Toast.makeText(this,"Data Tidak Ditemukan", Toast.LENGTH_LONG).show()

    }
}
