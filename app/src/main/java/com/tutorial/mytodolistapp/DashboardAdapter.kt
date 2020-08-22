package com.tutorial.mytodolistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutorial.mytodolistapp.DTO.ToDo

class DashboardAdapter(val context: Context, val list: MutableList<ToDo>) :
    RecyclerView.Adapter<DashboardAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_child_dashboard, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.toDoName.text = list[position].name

        holder.toDoName.setOnClickListener {
            //Send data to Item Activity with Intent
            val intent = Intent(context, ItemActivity::class.java)
            intent.putExtra(INTENT_TODO_ID, list[position].id)
            intent.putExtra(INTENT_TODO_NAME, list[position].name)
            context.startActivity(intent)
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val toDoName: TextView = v.findViewById(R.id.tv_todo_name)
    }
}