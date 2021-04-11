package com.jonathandarwin.todolist.database

import android.app.Application
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.jonathandarwin.todolist.database.dao.TodoDAO
import com.jonathandarwin.todolist.database.entity.Todo
import java.util.*

/**
 * Created By : Jonathan Darwin on April 10, 2021
 */
@Database(
    entities = [Todo::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun todoDao(): TodoDAO

    companion object {
        private var instance: AppDatabase? = null
        private val sLock = Object()

        fun getInstance(context: Context): AppDatabase {
            synchronized(sLock) {
                if(instance == null) {
                    instance = Room.databaseBuilder(context.applicationContext, AppDatabase::class.java, "Todo.db")
                        .allowMainThreadQueries()
                        .build()
                }
                return instance as AppDatabase
            }
        }
    }
}

