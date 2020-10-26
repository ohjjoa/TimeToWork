package com.example.timetowork

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*
import kotlin.collections.HashSet

class CalendarActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_calendar)

        val eventDays: HashSet<Date> = HashSet()
        eventDays.add(Date())

        //monthGap = pageCount - 1 - position
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.MONTH, calendar.get(Calendar.MONTH))
        findViewById<CalendarView>(R.id.calendar_view).updateCalendar(eventDays, calendar)
        //container.addView(view)
    }
}