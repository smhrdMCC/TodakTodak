package com.todaktodak.adapter

import android.annotation.SuppressLint
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.Interface.OnItemListener
import com.todaktodak.R
import com.todaktodak.model.emotiondate

class CalendarAdapter(
    private val dayList: ArrayList<String>,
    private val emotionList: ArrayList<emotiondate>?,
    private val onItemListener: OnItemListener
) :
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
    @SuppressLint("ResourceAsColor")
    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {

        // 날짜와 감정 변수에 담기
        var day = dayList[holder.adapterPosition]
        var check:Boolean = false
        holder.dayText.text = day

        var listleng = emotionList?.size

        holder.dayEmotion.visibility = View.INVISIBLE

        for (i in 0..listleng!!-1) {
            if (emotionList?.get(i)?.createdAt?.substring(8) == day || emotionList?.get(i)?.createdAt?.substring(8) == "0"+day) {
                if (emotionList?.get(i)?.emotion.toString() =="기쁨") {
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.joy)
                }else if(emotionList?.get(i)?.emotion.toString() =="행복"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.happy)
                }else if(emotionList?.get(i)?.emotion.toString() =="중립"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.neutrality)
                }else if(emotionList?.get(i)?.emotion.toString() =="불안"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.anxiety)
                }else if(emotionList?.get(i)?.emotion.toString() =="당황"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.panic)
                }else if(emotionList?.get(i)?.emotion.toString() =="분노"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.angry)
                }else if(emotionList?.get(i)?.emotion.toString() =="슬픔"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.sad)
                }else if(emotionList?.get(i)?.emotion.toString() =="혐오"){
                    check = true
                    holder.dayEmotion.visibility = View.VISIBLE
                    holder.dayEmotion.setImageResource(R.drawable.disgust)
                }
            }
        }

        val calendar_black = "#5A5C69"
        val calendar_blue = "#1569C7"
        val calendar_red = "#F75D59"

        val blue = Color.parseColor(calendar_blue)
        val red = Color.parseColor(calendar_red)
        val black = Color.parseColor(calendar_black)


        // 텍스트 색상 지정(토,일)
        if((position +1)%7==0){
            holder.dayText.setTextColor(blue)
        }else if(position==0||position%7==0){
            holder.dayText.setTextColor(red)
        }else{
            holder.dayText.setTextColor(black)
        }

        // 날짜 클릭 이벤트
        holder.itemView.setOnClickListener{
            // 인터페이스를 통해 날짜를 넘겨준다
            onItemListener.onItemClick(day, check)
        }
    }

    override fun getItemCount(): Int {
        return dayList.size
    }
}
