package com.jonathandarwin.todolist

import java.text.SimpleDateFormat
import java.util.*

/**
 * Created By : Jonathan Darwin on April 10, 2021
 */ 
object DatetimeUtil {

    fun getCurrentDate(): String {
        val date = Date()
        return SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault()).format(date)
    }

    fun convertDate(raw: String): String {
        val input = SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.getDefault())
        val output = SimpleDateFormat("dd MMM yyyy HH:mm", Locale.getDefault())

        val date = input.parse(raw) ?: Date()
        return output.format(date)
    }
}