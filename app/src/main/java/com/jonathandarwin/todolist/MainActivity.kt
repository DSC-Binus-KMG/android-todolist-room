package com.jonathandarwin.todolist

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.LinearLayoutManager
import com.jonathandarwin.todolist.database.AppDatabase
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), View.OnClickListener {

    private val todoAdapter: TodoAdapter by lazy { TodoAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setListener()
        setAdapter()
    }

    override fun onResume() {
        super.onResume()
        loadData()
    }

    private fun setListener() {
        fabAdd.setOnClickListener(this)
    }

    private fun setAdapter() {
        todoAdapter.setOnClickListener {
            val intent = Intent(this, InsertUpdateActivity::class.java)
            intent.putExtra(InsertUpdateActivity.TODO_ID, it.id)
            startActivity(intent)
        }

        todoAdapter.setOnDeleteListener {
            AlertDialog.Builder(this)
                .setTitle("Delete")
                .setMessage("Are you sure?")
                .setPositiveButton("Yes") {
                    _, _ ->
                        val result = AppDatabase.getInstance(this).todoDao().delete(it)
                        if(result >= 1) {
                            Toast.makeText(this, "Delete Success", Toast.LENGTH_SHORT).show()
                            loadData()
                        }
                        else {
                            Toast.makeText(this, "Error. Please try again", Toast.LENGTH_SHORT).show()
                        }

                }
                .setNegativeButton("No") {
                    dialog, _ -> dialog.dismiss()
                }
                .show()
        }

        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            adapter = todoAdapter
        }
    }

    private fun loadData() {
        val todoList = AppDatabase.getInstance(this).todoDao().get()
        todoAdapter.updateData(todoList)
    }

    override fun onClick(v: View?) {
        v?.let {
            when(it) {
                fabAdd -> {
                    val intent = Intent(this, InsertUpdateActivity::class.java)
                    startActivity(intent)
                }
            }
        }
    }
}