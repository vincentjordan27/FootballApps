package com.example.android.kade_sub_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.model.StandingModel
import kotlinx.android.synthetic.main.item_standing.view.*

class StandingAdapter(private val teams: List<StandingModel>)
    :RecyclerView.Adapter<StandingAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(standingModel: StandingModel){
            itemView.tv_name_item_standing.text = standingModel.name
            itemView.tv_played_item_standing.text = standingModel.played.toString()
            itemView.tv_goala_item_standing.text = standingModel.goalsagainst.toString()
            itemView.tv_goald_item_standing.text = standingModel.goalsdifference.toString()
            itemView.tv_goalsf_item_standing.text = standingModel.goalsfor.toString()
            itemView.tv_win_item_standing.text = standingModel.win.toString()
            itemView.tv_draw_item_standing.text = standingModel.draw.toString()
            itemView.tv_lost_item_standing.text = standingModel.loss.toString()
            itemView.tv_total_item_standing.text = standingModel.total.toString()

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
       return ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_standing,parent,false))
    }

    override fun getItemCount(): Int = teams.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(teams[position])
    }
}