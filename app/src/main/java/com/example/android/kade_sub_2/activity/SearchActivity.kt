package com.example.android.kade_sub_2.activity

import android.app.SearchManager
import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.ProgressBar
import android.widget.Toast
import androidx.appcompat.widget.Toolbar
import androidx.core.app.NavUtils
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.adapter.SearchAdapter
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.presenter.MatchSearchPresenter
import com.example.android.kade_sub_2.view.MatchSearchView
import com.example.android.kade_sub_2.utils.visible
import com.google.gson.GsonBuilder
import kotlinx.android.synthetic.main.activity_search.*

class SearchActivity : AppCompatActivity(),
    MatchSearchView {

    private lateinit var presenter: MatchSearchPresenter
    private lateinit var recyclerView: RecyclerView
    private lateinit var progressBar: ProgressBar
    private lateinit var adapter: SearchAdapter
    private var match: MutableList<MatchModel> = mutableListOf()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search)


        val toolbar = findViewById<Toolbar>(R.id.search_toolbar)
        setSupportActionBar(toolbar)
        supportActionBar?.title = "Search Match"
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


        adapter = SearchAdapter(match) {
            val intent = Intent(
                this,
                DetailActivity::class.java
            )
            intent.putExtra("data", it)
            startActivity(intent)
        }

        progressBar = pg_search
        recyclerView = rv_search
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter
        val apiRepository = ApiRepository()
        val gson = GsonBuilder().serializeNulls().create()
        presenter =
            MatchSearchPresenter(
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
                //Toast.makeText(this@SearchActivity,query,Toast.LENGTH_SHORT).show()
                recyclerView.invisible()
                progressBar.visible()
                presenter.getMatchSearch(query.toString())
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

    override fun getMatchSearch(data: List<MatchModel>) {


        Log.i("debugs","List Datang")
        recyclerView.visible()
        search_not_found.invisible()
        progressBar.invisible()
        match.clear()
        match.addAll(data)
        adapter.notifyDataSetChanged()


    }

    override fun empty() {
        recyclerView.invisible()
        search_not_found.visible()
        progressBar.invisible()
        Toast.makeText(this,"Data Tidak Ditemukan",Toast.LENGTH_LONG).show()
    }
}
