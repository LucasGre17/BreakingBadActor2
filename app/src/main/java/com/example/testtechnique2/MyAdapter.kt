package com.example.testtechnique2

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.testtechnique2.API.ActorItem

class MyAdapter(val context:Context, val actorList: List<ActorItem>):
    RecyclerView.Adapter<MyAdapter.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        var actorImage : ImageView
        var actorName : TextView
        var actorNickname : TextView
        var actorJob : TextView
        var actorSeason : TextView

        init {
            actorImage = itemView.findViewById(R.id.random_actor_image)
            actorName = itemView.findViewById(R.id.random_actor_name)
            actorNickname = itemView.findViewById(R.id.random_actor_nickname)
            actorJob = itemView.findViewById(R.id.random_actor_job)
            actorSeason = itemView.findViewById(R.id.random_actor_season)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemView = LayoutInflater.from(context).inflate(R.layout.row_items, parent, false)
        return ViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        Glide.with(holder.itemView.context)
            .load(actorList[position].img)
            .placeholder(R.mipmap.loading)
            .into(holder.actorImage)
        holder.actorName.text = context.resources.getString(R.string.random_actor_name).plus(" "+actorList[position].name)
        holder.actorNickname.text = context.resources.getString(R.string.random_actor_nickname).plus(" "+actorList[position].nickname)
        holder.actorJob.text = context.resources.getString(R.string.random_actor_job).plus(" "+actorList[position].occupation.toString())
        holder.actorSeason.text = context.resources.getString(R.string.random_actor_season).plus(" "+actorList[position].appearance)
    }

    override fun getItemCount(): Int {
        return actorList.size
    }
}