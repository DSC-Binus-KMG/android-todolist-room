package com.jonathandarwin.todolist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.jonathandarwin.todolist.database.AppDatabase
import com.jonathandarwin.todolist.database.entity.Todo
import kotlinx.android.synthetic.main.activity_insert_update.*

class InsertUpdateActivity : AppCompatActivity(), View.OnClickListener {

    companion object {
        val TODO_ID = "TODO_ID"

        val INSERT = "INSERT"
        val UPDATE = "UPDATE"
    }

    private var todoId: Long = 0
    private var todo: Todo? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_insert_update)

        setupUI()
        setListener()
    }

    private fun setupUI() {
        todoId = intent.getLongExtra(TODO_ID, 0)
        tvTitle.text = if(isInsert()) {
            "Insert Todo"
        } else {
            todo = AppDatabase.getInstance(this).todoDao().getById(todoId)
            etTitle.setText(todo?.title ?: "")
            etDescription.setText(todo?.description ?: "")
            "Update Todo"
        }
    }

    private fun setListener() {
        fabSave.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        v?.let{
            when(it) {
                fabSave -> {
                    val errorMessage = validateInput()
                    if(!errorMessage.isNullOrEmpty())
                        Toast.makeText(this, errorMessage, Toast.LENGTH_SHORT).show()
                    else {
                        // Save
                        if(isInsert()) {
                            // INSERT
                            val todo = Todo(0, etTitle.text.toString(),
                                etDescription.text.toString(), DatetimeUtil.getCurrentDate())
                            val result = AppDatabase.getInstance(this).todoDao().insert(todo)
                            handleResult(result)
                        }
                        else {
                            // UPDATE
                            todo?.let {
                                    item ->
                                item.title = etTitle.text.toString()
                                item.description = etDescription.text.toString()
                                item.datetime = DatetimeUtil.getCurrentDate()
                                val result = AppDatabase.getInstance(this).todoDao().update(item)
                                handleResult(result.toLong())
                            } ?: run {
                                Toast.makeText(this, "Error. Please try again", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }

    private fun isInsert(): Boolean = todoId == 0L

    private fun handleResult(result: Long) {
        if(result >= 1L) {
            Toast.makeText(this,
                if(isInsert()) "Insert Success" else "Update Success",
                Toast.LENGTH_SHORT).show()
            finish()
        }
        else{
            Toast.makeText(this, "Error. Please try again", Toast.LENGTH_SHORT).show()
        }
    }

    private fun validateInput(): String {
        if(etTitle.text.isNullOrEmpty()) return "Title must be filled"
        if(etDescription.text.isNullOrEmpty()) return "Description must be filled"
        return ""
    }
}