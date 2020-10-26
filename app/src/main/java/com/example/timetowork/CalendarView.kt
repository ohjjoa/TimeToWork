package com.example.timetowork

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.GridView
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.calendar_layout.view.*
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CalendarView: LinearLayout {
    lateinit var header: LinearLayout
    lateinit var gridView : GridView

    constructor(context: Context) : this(context, null)

    constructor(context: Context, attrs: AttributeSet?) : this(context, attrs, 0) {
        initControl(context, attrs!!) }

    constructor(context: Context, attrs: AttributeSet?, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    fun updateCalendar(events: HashSet<Date>, inputCalendar: Calendar) {
        val currentDate = Calendar.getInstance()
        val calendar = currentDate.clone() as Calendar

        // 타이틀 업데이트
        val simpleDateFormat = SimpleDateFormat("EEEE,MM월 d일,yyyy", Locale.KOREA)
        val dateToday: List<String> = simpleDateFormat.format(calendar.time).split(",")
        text_date_display_day.text = dateToday[0]
        text_date_display_date.text = dateToday[1]
        text_date_display_year.text = dateToday[2]

        val cells = ArrayList<Date>()
        calendar.set(Calendar.DAY_OF_MONTH, 1)
        // 일요일 부터 시작하기 때문에 1을 감산
        val monthBeginningCell = calendar.get(Calendar.DAY_OF_WEEK) - 1
        calendar.add(Calendar.DAY_OF_MONTH, -monthBeginningCell)

        while (cells.size < 48)
        {
            cells.add(calendar.time)
            calendar.add(Calendar.DAY_OF_MONTH, 1)
        }

        // 그리드 업데이트
        gridView.adapter = CalendarAdapter(context, cells, events, inputCalendar.get(Calendar.MONTH))
    }

    private fun assignUiElements() {
        header = findViewById(R.id.layout_calendar_header)
        gridView = findViewById(R.id.grid_calendar)
    }

   private fun initControl(context: Context, attrs: AttributeSet) {
       val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
       inflater.inflate(R.layout.calendar_layout, this)
       assignUiElements()
   }
}