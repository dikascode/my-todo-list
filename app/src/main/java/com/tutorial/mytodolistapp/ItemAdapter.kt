package com.tutorial.mytodolistapp

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.tutorial.mytodolistapp.DTO.ToDo
import com.tutorial.mytodolistapp.DTO.ToDoItem

class ItemAdapter(private val context: Context, private val dbHandler : DBHandler, private val list: MutableList<ToDoItem>) :
    RecyclerView.Adapter<ItemAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(context).inflate(R.layout.rv_child_item, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return list.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.itemName.text = list[position].itemName
        holder.itemName.isChecked = list[position].isCompleted

        holder.itemName.setOnClickListener {
            list[position].isCompleted = !list[position].isCompleted
            dbHandler.updateToDoItem(list[position])
        }
    }

    class ViewHolder(v: View) : RecyclerView.ViewHolder(v) {
        val itemName: CheckBox = v.findViewById(R.id.cb_item)
    }
}