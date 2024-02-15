package com.example.project

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.project.VO.feedbackVO

class CalendarAdapter(
    private val dayList: ArrayList<String>,
    private val emotionList: ArrayList<feedbackVO>,
    private val onItemListener: OnItemListener) :
    RecyclerView.Adapter<CalendarAdapter.ItemViewHolder>() {
    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayText: TextView = itemView.findViewById(R.id.dayText)
        val dayEmotion: ImageView = itemView.findViewById(R.id.dayEmoticon)
    }

    // 화면 설정
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.calendar_cell, parent, false)
        return ItemViewHolder(view)
    }

    // 데이터 설정
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        // 날짜와 감정 변수에 담기
        var day = dayList[holder.adapterPosition]

        holder.dayText.text = day



        for (i in 0..emotionList.size-1) {
            if (emotionList[i].createdAt?.substring(8) == day) {
                if (emotionList[i].emotionClassification=="기쁨") {
                    holder.dayEmotion.setImageResource(R.drawable.glad)
                }// else if () {
//
//                } else if () {
//
//                } else if () {
//
//                } else if () {
//
//                } else if () {
//
//                } else if () {
//
//                } else {
//
//                }
            }else{
                holder.dayEmotion.visibility = View.INVISIBLE
            }
        }

        // 텍스트 색상 지정(토,일)
        if((position +1)%7==0){
            holder.dayText.setTextColor(Color.BLUE)
        }else if(position==0||position%7==0){
            holder.dayText.setTextColor(Color.RED)
        }

        // 날짜 클릭 이벤트
        holder.itemView.setOnClickListener{
            // 인터페이스를 통해 날짜를 넘겨준다
            onItemListener.onItemClick(day)
        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}
