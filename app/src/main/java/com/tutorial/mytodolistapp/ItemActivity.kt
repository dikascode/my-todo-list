package com.tutorial.mytodolistapp

import android.app.AlertDialog
import android.content.DialogInterface
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import android.widget.EditText
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.tutorial.mytodolistapp.DTO.ToDo
import com.tutorial.mytodolistapp.DTO.ToDoItem
import kotlinx.android.synthetic.main.activity_item.*

class ItemActivity : AppCompatActivity() {

    lateinit var dbHandler: DBHandler

    var todoId : Long = -1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_item)
        setSupportActionBar(item_toolbar)

        //Enable back button
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        supportActionBar?.setHomeButtonEnabled(true)
        supportActionBar?.title = intent.getStringExtra(INTENT_TODO_NAME)
        todoId = intent.getLongExtra(INTENT_TODO_ID, -1)

        dbHandler = DBHandler(this)

        //Set layout manager
        rv_item.layoutManager = LinearLayoutManager(this)

        fab_item.setOnClickListener {
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
                    val item = ToDoItem()
                    item.itemName = toDoName.text.toString()
                    item.toDoId = todoId
                    item.isCompleted = false
                    dbHandler.addToDoItem(item)
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

    private fun refreshList() {
        rv_item.adapter = ItemAdapter(this, dbHandler, dbHandler.getToDoItems(todoId))
    }

    //Kill current activity on click of back button
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return if (item?.itemId == android.R.id.home) {
            finish()
            true
        } else
            super.onOptionsItemSelected(item)
    }
}