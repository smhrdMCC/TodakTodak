package com.todaktodak.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.todaktodak.R
import com.todaktodak.model.emotionContentVO
import com.todaktodak.view.DiaryListActivity

class DiaryListAdapter(
    private val DiaryList: List<emotionContentVO>?,
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
        var day = DiaryList?.get(holder.adapterPosition)?.createdAt
        var emotion = DiaryList?.get(holder.adapterPosition)?.emotionClassification
        var content = DiaryList?.get(holder.adapterPosition)?.diaryContent

        // 날짜 입력
        holder.dayMonthText.text = day?.substring(5)

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
            if (day != null) {
                onItemListener.onItemClick(day)
            }
        }
    }

    override fun getItemCount(): Int {
        return DiaryList?.size ?: itemCount
    }
}