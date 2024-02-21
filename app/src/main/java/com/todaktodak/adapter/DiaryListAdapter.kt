package com.todaktodak.adapter

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.view.DiaryListActivity

class DiaryListAdapter(
    private val dayMonthList: List<String?>?,
    private val emotionList: List<String?>?,
    private val contentList: List<String?>?,
    private val onItemListener: DiaryListActivity
) :
    RecyclerView.Adapter<DiaryListAdapter.ItemViewHolder>() {

    class ItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val dayMonthText: TextView = itemView.findViewById(R.id.listDayMonthText)
        val dayEmotionText: TextView = itemView.findViewById(R.id.listEmotionText)
        val dayDiaryContent: TextView = itemView.findViewById(R.id.listDiaryContent)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ItemViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.diary_list_cell, parent, false)
        return ItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: ItemViewHolder, position: Int) {
        // 날짜와 감정 변수에 담기
        var dayMonth = dayMonthList?.get(holder.adapterPosition)
        var emotion = emotionList?.get(holder.adapterPosition)
        var content = contentList?.get(holder.adapterPosition)

        // 날짜 입력
        holder.dayMonthText.text = dayMonth

        // 감정 입력
        if (emotion == "기쁨") {
            holder.dayEmotionText.text = "기뻤던 날"
        }else if(emotion == "슬픔"){

        }

        // 일기내용 입력
        holder.dayDiaryContent.text = content

        // 날짜 클릭 이벤트
        holder.itemView.setOnClickListener{
            // 인터페이스를 통해 날짜를 넘겨준다
            if (dayMonth != null) {
                onItemListener.onItemClick(dayMonth)
            }
        }
    }

    override fun getItemCount(): Int {
        return dayMonthList?.size ?: itemCount
    }
}