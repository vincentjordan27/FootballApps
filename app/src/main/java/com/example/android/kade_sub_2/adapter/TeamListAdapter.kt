package com.example.android.kade_sub_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.model.TeamModel
import kotlinx.android.synthetic.main.team_item.view.*

class TeamListAdapter(private val teams: List<TeamModel>, private val listener: (TeamModel) -> Unit)
    : RecyclerView.Adapter<TeamListAdapter.ViewHolder>()
{
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view){
        fun bindItems(teamModel: TeamModel, listener: (TeamModel) -> Unit){
            Glide.with(itemView).load(teamModel.strTeamBadge).into(itemView.team_item_img)
            itemView.tv_team_item_name.text = teamModel.strTeam
            itemView.tv_team_item_league.text = teamModel.strLeague
            itemView.setOnClickListener {
                listener(teamModel)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).inflate(
            R.layout.team_item, parent, false
        ))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(teams[position],listener)
    }

}