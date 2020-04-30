package com.example.android.kade_sub_2.activity

import android.annotation.SuppressLint
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.MenuItem
import android.widget.Button
import android.widget.Toast
import androidx.core.app.NavUtils
import com.bumptech.glide.Glide
import com.example.android.kade_sub_2.*
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.db.database
import com.example.android.kade_sub_2.model.MatchDetailModel
import com.example.android.kade_sub_2.model.MatchModel
import com.example.android.kade_sub_2.model.TeamMatchData
import com.example.android.kade_sub_2.presenter.MatchDetailPresenter
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.utils.visible
import com.example.android.kade_sub_2.view.MatchDetailView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select
import java.text.SimpleDateFormat
import java.util.*

class DetailActivity : AppCompatActivity(),
    MatchDetailView {
    private lateinit var datas: MatchModel
    private lateinit var postponed: String
    private lateinit var presenter: MatchDetailPresenter
    private lateinit var button: Button
    private lateinit var id: String
    private var isFavorite: Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        button = button_fav
        datas = intent.getParcelableExtra("data")
        nameHomeDetail.text = datas.homeTeam
        nameAwayDetail.text = datas.awayTeam
        postponed = datas.postponed.toString()
        id = datas.idEvent.toString()
        favoriteState()
        setFavorite()
        val request = ApiRepository()
        val gson = Gson()

        presenter =
            MatchDetailPresenter(
                this,
                request,
                gson
            )
        datas.idEvent?.let { presenter.getMatchDetail(it) }
        datas.homeTeamId?.let { presenter.getHomeData(it) }
        datas.awayTeamId?.let { presenter.getAwayData(it) }

        if (datas.date.isNullOrEmpty() || datas.time.isNullOrEmpty()) {
            date_detail.text = getString(R.string.notdata)
        } else
            date_detail.text =
                datas.time?.let { datas.date?.let { it1 -> toGMTFormat(it1, it).toString() } }

        button.setOnClickListener {
            if(isFavorite){
                removeFromFavorite()
            }else
                addToFavorite()

            isFavorite = !isFavorite
            setFavorite()
        }
    }

    override fun showLoading() {
        pg_detail.visible()
    }

    override fun hideLoading() {
        pg_detail.invisible()
    }

    override fun matchDetail(data: List<MatchDetailModel>) {
        if (postponed == "yes") {
            scoreHomeDetail.text = "-"
            scoreAwayDetail.text = "-"
            goalHomeTeam.text = getString(R.string.postponed)
            goalAwayTeam.text = getString(R.string.postponed)
            gkHomeTeam.text = getString(R.string.postponed)
            gkAwayTeam.text = getString(R.string.postponed)
            defHomeTeam.text = getString(R.string.postponed)
            defAwayTeam.text = getString(R.string.postponed)
            midHomeTeam.text = getString(R.string.postponed)
            midAwayTeam.text = getString(R.string.postponed)
            fowHomeTeam.text = getString(R.string.postponed)
            fowAwayTeam.text = getString(R.string.postponed)
            subHomeTeam.text = getString(R.string.postponed)
            subAwayTeam.text = getString(R.string.postponed)
        } else {
            scoreHomeDetail.text = data[0].homeScore
            scoreAwayDetail.text = data[0].awayScore
            goalHomeTeam.text = data[0].homeGoal
            goalAwayTeam.text = data[0].awayGoal
            gkHomeTeam.text = data[0].gkHome
            gkAwayTeam.text = data[0].gkAway
            defHomeTeam.text = data[0].defHome
            defAwayTeam.text = data[0].defAway
            midHomeTeam.text = data[0].midHome
            midAwayTeam.text = data[0].midAway
            fowHomeTeam.text = data[0].fowHome
            fowAwayTeam.text = data[0].fowAway
            subHomeTeam.text = data[0].subsHome
            subAwayTeam.text = data[0].subsAway
            Log.i("array", data[0].toString())
        }

    }

    override fun homeTeam(data: List<TeamMatchData>) {
        Glide.with(this@DetailActivity).load(data[0].teamBadge)
            .into(img_home_detail)
    }

    override fun awayTeam(data: List<TeamMatchData>) {
        Glide.with(this@DetailActivity).load(data[0].teamBadge)
            .into(img_away_detail)
    }

    @SuppressLint("SimpleDateFormat")
    fun toGMTFormat(date: String, time: String): Date? {
        val formatter = SimpleDateFormat("yy-MM-dd")
        formatter.timeZone = TimeZone.getTimeZone("UTC")
        val dateTime = "$date $time"
        return formatter.parse(dateTime)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            android.R.id.home -> {
                NavUtils.navigateUpFromSameTask(this)
            }
        }
        return super.onOptionsItemSelected(item)
    }


    private fun addToFavorite(){
        try {
            val home: String
            val away: String
            if(datas.homeScore.equals(null)){
                home = "postponed"
            }else{
                home = datas.homeScore.toString()
            }

            if(datas.awayScore.equals(null)){
                away = "postponed"
            }else{
                away = datas.awayScore.toString()
            }
            database.use {
                insert(MatchModel.TABLE_FAVORITE,
                    MatchModel.ID_EVENT to datas.idEvent,
                    MatchModel.HOME_TEAM to datas.homeTeam,
                    MatchModel.AWAY_TEAM to datas.awayTeam,
                    MatchModel.HOME_TEAM_ID to datas.homeTeamId,
                    MatchModel.AWAY_TEAM_ID to datas.awayTeamId,
                    MatchModel.DATE_EVENT to datas.date,
                    MatchModel.TIME_LOCAL to datas.time,
                    MatchModel.TYPE to datas.type,
                    MatchModel.HOME_SCORE to home,
                    MatchModel.AWAY_SCORE to away,
                    MatchModel.POSTPONED to datas.postponed
                )
            }
            Toast.makeText(this@DetailActivity,"Added to Favorite",Toast.LENGTH_SHORT).show()

        } catch (e : SQLiteConstraintException){
            Toast.makeText(this@DetailActivity, e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFromFavorite(){
        try {
            database.use {
                delete(MatchModel.TABLE_FAVORITE, "(ID_EVENT = {id})", "id" to id)
            }
            Toast.makeText(this@DetailActivity,"Deleted from Favorite",Toast.LENGTH_SHORT).show()

        } catch (e : SQLiteConstraintException){
            Toast.makeText(this@DetailActivity, e.localizedMessage,Toast.LENGTH_SHORT).show()
        }
    }

    private  fun favoriteState(){
        database.use {
            val result = select(MatchModel.TABLE_FAVORITE)
                .whereArgs("(ID_EVENT = {id})",
                "id" to id)
            val favorite = result.parseList(classParser<MatchModel>())
            if(!favorite.isEmpty())
                isFavorite = true
        }
    }

    private fun setFavorite(){
        if(isFavorite){
            button.text = getString(R.string.del)
            button.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            button.setTextColor(resources.getColor(android.R.color.white))

        }else {
            button.text = getString(R.string.add)
            button.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
            button.setTextColor(resources.getColor(android.R.color.white))
        }
    }
}
