package com.template.technocratia.presentation.utils

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.template.technocratia.R
import com.template.technocratia.domain.entities.UserStored

class UserRecyclerViewAdapter(private val users: List<UserStored> = listOf()) :
    RecyclerView.Adapter<UserRecyclerViewAdapter.MyViewHolder>() {


    class MyViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val img: ImageView = itemView.findViewById(R.id.userCardImg)
        val fullName: TextView = itemView.findViewById(R.id.userCardFullName)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            LayoutInflater.from(parent.context)
                .inflate(R.layout.user_card, parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val it = users[position]
        Glide.with(holder.itemView.context)
            .load(it.photo)
            .circleCrop()
            .placeholder(R.drawable.ic_launcher_foreground)
            .error(R.drawable.ic_launcher_foreground)
            .into(holder.img)

        holder.fullName.text = it.fullName
    }

    override fun getItemCount(): Int = users.size
}