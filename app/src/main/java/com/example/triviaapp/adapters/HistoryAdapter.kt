package com.example.triviaapp.adapters

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.triviaapp.R
import com.example.triviaapp.database.Answers
import org.w3c.dom.Text


class HistoryAdapter(val context: Context,val historyList: List<Answers>) : RecyclerView.Adapter<HistoryAdapter.MyViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HistoryAdapter.MyViewHolder {
        val view: View = LayoutInflater.from(parent.context).inflate(R.layout.history_list, parent, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return historyList.size
    }

    override fun onBindViewHolder(holder: HistoryAdapter.MyViewHolder, position: Int) {
        val answers = historyList[position]
        holder.tvName.text = "Name : "+answers.name
        holder.tvCricketer.text = answers.cricketer
        holder.tvColor.text = answers.color
        holder.tvDate.text = answers.date
        holder.tvGameNo.text = "GAME NO : "+answers.id
    }

    inner class MyViewHolder(itemView:View) : RecyclerView.ViewHolder(itemView) {
        val tvName : TextView = itemView.findViewById(R.id.tvName)
        val tvDate : TextView = itemView.findViewById(R.id.tvDate)
        val tvGameNo : TextView = itemView.findViewById(R.id.tvGameNo)
        val tvCricketer : TextView = itemView.findViewById(R.id.tvCricketer)
        val tvColor : TextView = itemView.findViewById(R.id.tvColor)
    }
}