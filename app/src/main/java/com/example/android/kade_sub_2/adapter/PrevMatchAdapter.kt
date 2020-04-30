package com.example.android.kade_sub_2.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.model.MatchModel
import kotlinx.android.synthetic.main.item_match_prev.view.*
import java.text.SimpleDateFormat
import java.util.*

class PrevMatchAdapter (private val match: List<MatchModel>, private val listener: (MatchModel)-> Unit)
    : RecyclerView.Adapter<PrevMatchAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        fun bindItems(match: MatchModel, listener: (MatchModel) -> Unit){
            itemView.homeTeamItem.text = match.homeTeam
            itemView.awayTeamItem.text= match.awayTeam
            if(match.date.equals(null) || match.time.equals(null)){
                itemView.date_item.text = itemView.context.getString(R.string.nodata)
            }
            else
                itemView.date_item.text = match.time?.let { match.date?.let { it1 -> toGMTFormat(it1, it).toString() } }
            if(match.postponed.equals("yes")){
                itemView.scoreHomeTeamItem.text = "-"
                itemView.scoreAwayTeamItem.text = "-"
            }else {
                itemView.scoreHomeTeamItem.text = match.homeScore.toString()
                itemView.scoreAwayTeamItem.text = match.awayScore.toString()
            }
            itemView.setOnClickListener {
                listener(match)
            }

        }


        @SuppressLint("SimpleDateFormat")
        fun toGMTFormat(date: String, time: String): Date? {
            val formatter = SimpleDateFormat("yy-MM-dd")
            formatter.timeZone = TimeZone.getTimeZone("UTC")
            val dateTime = "$date $time"
            return formatter.parse(dateTime)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.item_match_prev,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        Log.i("debug",match.size.toString())
        return match.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItems(match[position],listener)
    }



}