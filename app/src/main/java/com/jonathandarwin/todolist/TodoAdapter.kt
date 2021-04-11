package com.jonathandarwin.todolist

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.jonathandarwin.todolist.database.entity.Todo
import kotlinx.android.synthetic.main.list_todo_item.view.*

/**
 * Created By : Jonathan Darwin on April 10, 2021
 */ 
class TodoAdapter : RecyclerView.Adapter<TodoAdapter.TodoViewHolder>() {

    private val todoList = arrayListOf<Todo>()
    private var clickCallback: ((Todo) -> Unit)? = null
    private var deleteCallback: ((Todo) -> Unit)? = null

    inner class TodoViewHolder(val view: View): RecyclerView.ViewHolder(view) {
        fun bind(todo: Todo) {
            view.tvTitle.text = todo.title
            view.tvDescription.text = todo.description
            view.tvDateTime.text = "Last Updated : ${DatetimeUtil.convertDate(todo.datetime)}"

            view.rootView.setOnClickListener {
                clickCallback?.invoke(todo)
            }

            view.icDelete.setOnClickListener{
                deleteCallback?.invoke(todo)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TodoViewHolder =
        TodoViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.list_todo_item, parent, false))

    override fun getItemCount(): Int = todoList.size

    override fun onBindViewHolder(holder: TodoViewHolder, position: Int) {
        holder.bind(todoList[position])
    }

    fun updateData(newList: List<Todo>) {
        todoList.clear()
        todoList.addAll(newList)
        notifyDataSetChanged()
    }

    fun setOnClickListener(clickCallback: (Todo) -> Unit) {
        this.clickCallback = clickCallback
    }

    fun setOnDeleteListener(deleteCallback: (Todo) -> Unit) {
        this.deleteCallback = deleteCallback
    }
}