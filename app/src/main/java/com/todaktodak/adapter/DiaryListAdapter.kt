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
        var check: Boolean = false

        // 날짜 입력
        holder.dayMonthText.text = day?.substring(5)

        // 감정 입력
        if (emotion == "기쁨") {
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 기뻐요"
        }else if(emotion =="행복"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 행복해요"
        }else if(emotion =="중립"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 나쁘지 않은 날이에요"
        }else if(emotion =="불안"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 불안해요"
        }else if(emotion =="당황"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 당황스러워요"
        }else if(emotion =="분노"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 화가 나요"
        }else if(emotion =="슬픔"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 슬퍼요"
        }else if(emotion =="혐오"){
            check = true
            holder.dayEmotionText.text = "일 감정 상태 : 싫어요"
        }

        // 일기내용 입력
        holder.dayDiaryContent.text = content

        // 날짜 클릭 이벤트
        holder.itemView.setOnClickListener{
            // 인터페이스를 통해 날짜를 넘겨준다
            onItemListener.onItemClick(day!!, check)

        }
    }

    override fun getItemCount(): Int {
        return DiaryList?.size ?: itemCount
    }
}