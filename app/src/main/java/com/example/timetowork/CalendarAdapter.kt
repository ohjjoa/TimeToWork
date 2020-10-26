package com.example.timetowork

import android.content.Context
import android.graphics.Color
import android.graphics.Typeface
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import java.util.*

class CalendarAdapter(
    context: Context, days: ArrayList<Date>, eventDays: HashSet<Date>,
    inputMonth: Int) :
    ArrayAdapter<Date>(context, R.layout.item_calendar_day, days) {

    private val eventDays: HashSet<Date>? = null
    private val inflater : LayoutInflater = LayoutInflater.from(context)
    private val inputMonth = inputMonth - 1

    override fun getView(position: Int, view: View?, parent: ViewGroup) : View {

        var view = view
        val calendar = Calendar.getInstance()
        val date = getItem(position)
        calendar.time = date

        val day = calendar.get(Calendar.DATE)
        val month = calendar.get(Calendar.MONTH)
        val year = calendar.get(Calendar.YEAR)

        // 오늘 날짜 갖고옴
        val today = Date()
        val calendarToday = Calendar.getInstance()
        calendarToday.time = today

        // 날짜 디자인으로 먼저 만들어 둔 item_calendar_day inflate
        if (view == null) {
            view = inflater.inflate(R.layout.item_calendar_day, parent, false)
        }

        //여기에서 기호에 따라 뷰의 생김새와 일자의 디자인을 변경이 가능
        (view as TextView).setTypeface(null, Typeface.NORMAL)
        view.setTextColor(Color.parseColor("#56a6a9"))

        // 현재 달, 현재 년도 아니면 일자 색 회색
        if (month != inputMonth || year != calendarToday.get(Calendar.YEAR)) {
            (view).setTextColor(Color.parseColor("#E0E0E0"))
        }

        if (month == calendarToday.get(Calendar.MONTH) && year == calendarToday.get(Calendar.YEAR) &&
            day == calendarToday.get(Calendar.DATE)) {
            // if it is today, set it to blue/bold
            (view).setTextColor(Color.WHITE)
            view.gravity = Gravity.CENTER
            view.setBackgroundResource(R.drawable.round_textview)
        }

         //if this day has an event, specify event image
        if (eventDays != null) {
            for (eventDate in eventDays) {
                if (eventDate.date === day && eventDate.month === month && eventDate.year === year) {
                    // mark this day for event
                    break
                }
            }
        }

        // clear styling
//        tvItemViewCalendar.setTypeface(null, Typeface.NORMAL)
//        text_item_view_calendar.setTextColor(Color.BLACK)

        // 날짜를 텍스트뷰에 설정
        (view as TextView).text = calendar[Calendar.DATE].toString()
        return view
    }
}