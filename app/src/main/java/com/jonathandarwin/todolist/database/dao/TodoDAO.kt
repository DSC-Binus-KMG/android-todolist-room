package com.jonathandarwin.todolist.database.dao

import androidx.room.*
import com.jonathandarwin.todolist.database.entity.Todo

/**
 * Created By : Jonathan Darwin on April 10, 2021
 */ 
@Dao
interface TodoDAO {
    @Insert
    fun insert(todo: Todo): Long

    @Update
    fun update(todo: Todo): Int

    @Delete
    fun delete(todo: Todo): Int

    @Query("SELECT * FROM Todo ORDER BY datetime DESC")
    fun get(): List<Todo>

    @Query("SELECT * FROM Todo WHERE id = :id")
    fun getById(id: Long): Todo
}

