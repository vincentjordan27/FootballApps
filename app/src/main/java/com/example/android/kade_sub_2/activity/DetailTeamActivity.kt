package com.example.android.kade_sub_2.activity

import android.database.sqlite.SQLiteConstraintException
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.api.ApiRepository
import com.example.android.kade_sub_2.db.database
import com.example.android.kade_sub_2.model.TeamMatchData
import com.example.android.kade_sub_2.model.TeamModel
import com.example.android.kade_sub_2.presenter.TeamDetailPresenter
import com.example.android.kade_sub_2.utils.invisible
import com.example.android.kade_sub_2.utils.visible
import com.example.android.kade_sub_2.view.TeamDetailView
import com.google.gson.Gson
import kotlinx.android.synthetic.main.activity_detail_team.*
import org.jetbrains.anko.db.classParser
import org.jetbrains.anko.db.delete
import org.jetbrains.anko.db.insert
import org.jetbrains.anko.db.select

class DetailTeamActivity : AppCompatActivity(), TeamDetailView {

    private lateinit var data: TeamModel
    private lateinit var presenter: TeamDetailPresenter
    private lateinit var button: Button
    private var isFavorite: Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail_team)

        button = fav_team_detail

        data = intent.getParcelableExtra("data")
        Glide.with(this).load(data.strTeamBadge).into(img_team_detail)
        name_team_detail.text = data.strTeam
        league_team_detail.text = data.strLeague
        val request = ApiRepository()
        val gson = Gson()
        presenter = TeamDetailPresenter(this, request, gson)
        presenter.getTeamDetail(data.idTeam)
        favoriteState()
        setFavorite()

        button.setOnClickListener {
            if (isFavorite) {
                removeFavorite()
            } else
                addFavorite()

            isFavorite = !isFavorite
            setFavorite()
        }
    }

    override fun hideLoading() {
        prog_team_detail.invisible()
    }

    override fun getTeamData(data: List<TeamMatchData>) {
        year_team_detail.text = data[0].teamYear
        desc_team_detail.text = data[0].teamDesc
        stadium_team_detail.text = data[0].teamStadium
    }

    override fun showLoading() {
        prog_team_detail.visible()
    }

    private fun addFavorite() {
        try {
            database.use {
                insert(
                    TeamModel.TEAM_FAVORITE,
                    TeamModel.ID_TEAM to data.idTeam,
                    TeamModel.STR_TEAM to data.strTeam,
                    TeamModel.STR_TEAM_BADGE to data.strTeamBadge,
                    TeamModel.STR_LEAGUE to data.strLeague
                )
            }
            Toast.makeText(this@DetailTeamActivity, "Added to Favorite", Toast.LENGTH_SHORT).show()

        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this@DetailTeamActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun removeFavorite() {
        try {
            database.use {
                delete(TeamModel.TEAM_FAVORITE, "(ID_TEAM = {id})", "id" to data.idTeam.toString())
            }
            Toast.makeText(this@DetailTeamActivity, "Deleted from Favorite", Toast.LENGTH_SHORT)
                .show()

        } catch (e: SQLiteConstraintException) {
            Toast.makeText(this@DetailTeamActivity, e.localizedMessage, Toast.LENGTH_SHORT).show()
        }
    }

    private fun favoriteState() {
        database.use {
            val result = select(TeamModel.TEAM_FAVORITE)
                .whereArgs(
                    "(ID_TEAM = {id})",
                    "id" to data.idTeam.toString()
                )
            val favorite = result.parseList(classParser<TeamModel>())
            if (!favorite.isEmpty())
                isFavorite = true
        }
    }

    private fun setFavorite() {
        if (isFavorite) {
            button.text = getString(R.string.del)
            button.setBackgroundColor(resources.getColor(android.R.color.holo_red_light))
            button.setTextColor(resources.getColor(android.R.color.white))

        } else {
            button.text = getString(R.string.add)
            button.setBackgroundColor(resources.getColor(android.R.color.holo_green_light))
            button.setTextColor(resources.getColor(android.R.color.white))
        }
    }
}
