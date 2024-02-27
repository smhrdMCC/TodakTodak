package com.todaktodak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.model.replyDiary
import com.todaktodak.view.ReplyDiaryListActivity

class ReplyDiaryListAdapter(
    private val ReplyDiaryList: ArrayList<replyDiary>?,
    private val onItemListener: ReplyDiaryListActivity
) :
    RecyclerView.Adapter<ReplyDiaryListAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val listUserEmail: TextView = itemView.findViewById(R.id.listUserEmail)
        val listUserNick: TextView = itemView.findViewById(R.id.listUserNick)
        val listDiaryContent: TextView = itemView.findViewById(R.id.listDiaryContent)
    }
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.reply_diary_room_list_cell, parent, false)
        return ItemViewHolder(view)
    }
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        var email = ReplyDiaryList?.get(holder.adapterPosition)?.email
        var nick = ReplyDiaryList?.get(holder.adapterPosition)?.nick
        var content = ReplyDiaryList?.get(holder.adapterPosition)?.content

        holder.listUserEmail.text = email
        holder.listUserEmail.visibility = View.INVISIBLE
        holder.listUserNick.text = nick
        holder.listDiaryContent.text = content

        holder.itemView.setOnClickListener{
            onItemListener.onItemClick(email)
        }
    }
    override fun getItemCount(): Int {
        return ReplyDiaryList?.size ?: itemCount
    }
}