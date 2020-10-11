package com.example.timetowork

import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.calendar_layout.view.*
import java.util.*
import kotlin.collections.ArrayList

class CalendarView @JvmOverloads constructor(
    context: Context, attrs: AttributeSet? = null, defStyleAttrs: Int = 0
) : LinearLayout(context, attrs, defStyleAttrs){
    init {
        LayoutInflater.from(context).inflate(R.layout.calendar_layout, this, true)
    }
}