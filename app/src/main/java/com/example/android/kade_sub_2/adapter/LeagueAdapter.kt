package com.example.android.kade_sub_2.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.android.kade_sub_2.R
import com.example.android.kade_sub_2.model.League

class LeagueAdapter(
    private val list: List<League>,
    private val listener: (League) -> Unit
) : RecyclerView.Adapter<LeagueAdapter.ViewHolder>()
{
    class ViewHolder( private val containerView: View): RecyclerView.ViewHolder(containerView) {

        private val name: TextView = containerView.findViewById(R.id.tv_league_item)
        private val image: ImageView = containerView.findViewById(R.id.img_league_item)

        fun bindItem(item: League, listener: (League) -> Unit){
            Glide.with(containerView.context).load(item.image).into(image)
            name.text = item.name
            itemView.setOnClickListener {
                listener(item)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.league_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int = list.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindItem(list[position],listener)
    }

}