package com.example.android.kade_sub_2.adapter

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.model.MatchModel
import java.text.SimpleDateFormat
import java.util.*

class SearchAdapter (private val match: List<MatchModel>, private val listener: (MatchModel)-> Unit)
    : RecyclerView.Adapter<SearchAdapter.ViewHolder>(){
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        private var homeTeam: TextView = view.findViewById(R.id.homeTeamItem)
        private var awayTeam: TextView = view.findViewById(R.id.awayTeamItem)
        private var homeTeamScore: TextView = view.findViewById(R.id.scoreHomeTeamItem)
        private var awayTeamScore: TextView = view.findViewById(R.id.scoreAwayTeamItem)
        private var dateTextView: TextView = view.findViewById(R.id.date_item)

        fun bindItems(match: MatchModel, listener: (MatchModel) -> Unit){

                homeTeam.text = match.homeTeam
                awayTeam.text = match.awayTeam
                if(match.date.equals(null) || match.time.equals(null)){
                    dateTextView.text = itemView.context.getString(R.string.nodata)
                }
                else
                dateTextView.text = match.time?.let { match.date?.let { it1 -> toGMTFormat(it1, it).toString() } }

                if(match.postponed.equals("yes") || homeTeamScore.equals(null)){
                    awayTeamScore.text = "-"
                    homeTeamScore.text = "-"
                }else {
                    homeTeamScore.text = match.homeScore.toString()
                    awayTeamScore.text = match.awayScore.toString()
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