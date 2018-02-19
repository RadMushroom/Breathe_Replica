package com.example.radmushroom.breathe.util

import android.app.Activity
import android.content.SharedPreferences
import java.util.*


class Prefs(activity: Activity) {
    private var preferences: SharedPreferences = activity.getPreferences(Activity.MODE_PRIVATE)

    fun setBreaths(breaths: Int){
        preferences.edit().putInt("breaths", breaths).apply()
    }

    fun getBreaths(): Int {
        return preferences.getInt("breaths", 0)
    }

    fun setSessions(session: Int){
        preferences.edit().putInt("session", session).apply()
    }

    fun getSessions(): Int{
        return preferences.getInt("session", 0)
    }

    fun setDate(milliseconds: Long){
        preferences.edit().putLong("seconds", milliseconds).apply()
    }

    fun getDate(): String{
        val secondDate: Long = preferences.getLong("seconds", 0 )
//        val amOrPm: String
//        val a: Int = Calendar.AM_PM
//        if (a == Calendar.AM){
//            amOrPm = "AM"
//        } else amOrPm = "PM"
        val calendar = Calendar.getInstance()
        calendar.timeInMillis = secondDate
        return " ${calendar[Calendar.HOUR_OF_DAY]}: ${calendar[Calendar.MINUTE]}"
    }
}