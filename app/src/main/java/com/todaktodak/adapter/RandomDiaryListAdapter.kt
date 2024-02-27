package com.todaktodak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.model.randomDiary
import com.todaktodak.view.RandomDiaryListActivity

class RandomDiaryListAdapter(
    private val RandomDiaryList: ArrayList<randomDiary>?,
    private val onItemListener: RandomDiaryListActivity
) :
    RecyclerView.Adapter<RandomDiaryListAdapter.ItemViewHolder>() {

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

        var email = RandomDiaryList?.get(holder.adapterPosition)?.fromUser
        var nick = RandomDiaryList?.get(holder.adapterPosition)?.nick
        var content = RandomDiaryList?.get(holder.adapterPosition)?.content

        holder.listUserEmail.text = email
        holder.listUserEmail.visibility = View.INVISIBLE
        holder.listUserNick.text = nick
        holder.listDiaryContent.text = content


        // 클릭 이벤트
        holder.itemView.setOnClickListener{
            onItemListener.onItemClick(email, nick, content)
        }
    }

    override fun getItemCount(): Int {
        return RandomDiaryList?.size ?: itemCount
    }
}