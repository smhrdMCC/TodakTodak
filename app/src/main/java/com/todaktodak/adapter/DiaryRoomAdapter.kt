package com.todaktodak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.model.replyDiary
import com.todaktodak.retrofit.usersingleton

class DiaryRoomAdapter(
    private val diaryInRoomList: ArrayList<replyDiary>?
) : RecyclerView.Adapter<DiaryRoomAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diaryRoomDate: TextView = itemView.findViewById(R.id.diaryRoomDate)
        val diaryRoomContent: TextView = itemView.findViewById(R.id.diaryRoomContent)
        val layout: LinearLayout = itemView.findViewById(R.id.diaryRoomLayout)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_room_cell, parent, false)
        return ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryInRoomList?.size ?: itemCount
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var Email = diaryInRoomList?.get(holder.adapterPosition)?.email
        var date = diaryInRoomList?.get(holder.adapterPosition)?.date
        var content = diaryInRoomList?.get(holder.adapterPosition)?.content

        holder.diaryRoomDate.text = date
        holder.diaryRoomContent.text = content
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams

        if(Email == usersingleton.userEmail){
            layoutParams.marginStart = 0
        }else{
            layoutParams.marginEnd = 0
        }

    }
}