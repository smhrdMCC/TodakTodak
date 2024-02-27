package com.todaktodak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.model.diaryInRoom
import com.todaktodak.retrofit.usersingleton
import com.todaktodak.view.ReplyDiaryListActivity

class DiaryRoomAdapter(
    private val diaryInRoomList: ArrayList<diaryInRoom>?
) : RecyclerView.Adapter<DiaryRoomAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val diaryRoomDate: TextView = itemView.findViewById(R.id.diaryRoomDate)
        val diaryRoomContent: TextView = itemView.findViewById(R.id.diaryRoomContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_room_cell, parent, false)
        return DiaryRoomAdapter.ItemViewHolder(view)
    }

    override fun getItemCount(): Int {
        return diaryInRoomList?.size ?: itemCount
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        var toEmail = diaryInRoomList?.get(holder.adapterPosition)?.toEmail
        var fromEmail = diaryInRoomList?.get(holder.adapterPosition)?.fromEmail
        var date = diaryInRoomList?.get(holder.adapterPosition)?.date
        var content = diaryInRoomList?.get(holder.adapterPosition)?.content

        holder.diaryRoomDate.text = date
        holder.diaryRoomContent.text = content
        val layoutParams = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams

        if(toEmail == usersingleton.userEmail){
            layoutParams.marginStart = 0
        }else{
            layoutParams.marginEnd = 0
        }

    }
}