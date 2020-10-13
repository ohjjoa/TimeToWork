package com.example.timetowork

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.ImageView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.calendar_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarView: LinearLayout {
    lateinit var header: LinearLayout
    lateinit var gridView : GridView
    lateinit var imageCalendarPrev : ImageView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initControl(context, attrs!!) }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private fun assignUiElements() {
        header = findViewById(R.id.layout_calendar_header)
        gridView = findViewById(R.id.grid_calendar)
        imageCalendarPrev = image_calendar_prev
    }

    fun updateCalendar(events: HashSet<Date>, inputCalendar: Calendar) {
        val cells = ArrayList<Date>()

        inputCalendar.set(Calendar.DAY_OF_YEAR, 1)

        val monthBeginningCell = inputCalendar.get(Calendar.DAY_OF_WEEK - 1)

        inputCalendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        // 타이틀 업데이트
        val simpleDateFormat = SimpleDateFormat("EEEE,d MMM,yyyy")
        val dateToday: List<String> = simpleDateFormat.format(inputCalendar.time).split(",")
        text_date_display_day.text = dateToday[0]
        text_date_display_date.text = dateToday[1]
        text_date_display_year.text = dateToday[2]

        // 그리드에 집어 넣을 cell 들의 setup
        while (cells.size < (Calendar.DAY_OF_MONTH) +
            inputCalendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
            cells.add(inputCalendar.time)
            inputCalendar.add(Calendar.MONTH, 1)
        }

        // 그리드 업데이트
        gridView.adapter = CalendarAdapter(
            context,
            cells,
            events,
            inputCalendar.get(Calendar.MONTH)
        )
    }

   private fun initControl(context: Context, attrs: AttributeSet) {
       val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
       inflater.inflate(R.layout.calendar_layout, this)
       assignUiElements()
   }
}