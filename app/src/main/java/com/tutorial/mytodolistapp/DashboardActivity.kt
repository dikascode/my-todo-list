package com.tutorial.mytodolistapp

import android.content.Context
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.tutorial.mytodolistapp.DTO.ToDo
import kotlinx.android.synthetic.main.activity_dashboard.*

class DashboardActivity : AppCompatActivity() {
    lateinit var dbHandler: DBHandler
    lateinit var recyclerView: RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)
        setSupportActionBar(dashboard_toolbar)
        title = "Dashboard"

        //create object of database
        dbHandler = DBHandler(this)

        //set layout manager for recyclerview
        recyclerView = findViewById(R.id.rv_dashboard)
        recyclerView.layoutManager = LinearLayoutManager(this)

        fab_dashboard.setOnClickListener {
            //setup alert dialog
            val dialog = AlertDialog.Builder(this)

            //Inflate layout file
            val view = layoutInflater.inflate(R.layout.dialog_dashboard, null)
            val toDoName: EditText = view.findViewById<EditText>(R.id.ev_todo)
            dialog.setView(view)

            //Set up Dialog positive button
            dialog.setPositiveButton("Add") { _: DialogInterface, _: Int ->
                //Add Data into database
                if (toDoName.text.isNotEmpty()) {
                    val toDo = ToDo()
                    toDo.name = toDoName.text.toString()
                    dbHandler.addToDo(toDo)
                    refreshList()
                }
            }

            //Set up Dialog Negative button
            dialog.setNegativeButton("Cancel") { _: DialogInterface, _: Int ->

            }

            dialog.show()
        }
    }

    override fun onResume() {
        refreshList()
        super.onResume()
    }

    //Refresh recyclerview after adding item
    private fun refreshList() {
        recyclerView.adapter = DashboardAdapter(this, dbHandler.getToDos())
    }



}