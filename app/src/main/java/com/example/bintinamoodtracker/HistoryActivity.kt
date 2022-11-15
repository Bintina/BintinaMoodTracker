package com.example.bintinamoodtracker

import android.content.Context
import android.os.Bundle
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.databinding.ActivityHistoryBinding
import com.example.bintinamoodtracker.myApp.Companion.HISTORY_BAR_INCREMENT
import com.example.bintinamoodtracker.myApp.Companion.arrayOfBackgrounds
import com.example.bintinamoodtracker.myApp.Companion.fiveDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.fourDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.sevenDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.sixDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.threeDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.twoDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.yesterdayMood

class HistoryActivity : AppCompatActivity() {

    lateinit var historyBinding: ActivityHistoryBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding.root)

        initialiseHistoryVariables(this)
        setViewBackgrounds()
        setViewWidths(this)
    }

    fun setViewBackgrounds() {
        historyBinding.apply {
            yesterday.setBackgroundColor(getColor(arrayOfBackgrounds[yesterdayMood.moodScore]))
            yesterday.text = yesterdayMood.comment.toString()

            twoDays.setBackgroundColor(getColor(arrayOfBackgrounds[twoDaysAgoMood.moodScore]))
            twoDays.text = twoDaysAgoMood.comment.toString()

            threeDays.setBackgroundColor(getColor(arrayOfBackgrounds[threeDaysAgoMood.moodScore]))
            threeDays.text = threeDaysAgoMood.comment.toString()

            fourDays.setBackgroundColor(getColor(arrayOfBackgrounds[fourDaysAgoMood.moodScore]))
            fourDays.text = fourDaysAgoMood.comment.toString()

            fiveDays.setBackgroundColor(getColor(arrayOfBackgrounds[fiveDaysAgoMood.moodScore]))
            fiveDays.text = fiveDaysAgoMood.comment.toString()

            sixDays.setBackgroundColor(getColor(arrayOfBackgrounds[sixDaysAgoMood.moodScore]))
            sixDays.text = sixDaysAgoMood.comment.toString()

            sevenDays.setBackgroundColor(getColor(arrayOfBackgrounds[sevenDaysAgoMood.moodScore]))
            sevenDays.text = sevenDaysAgoMood.comment.toString()
        }
    }

    private fun setViewWidths(context: Context) {
        historyBinding.apply {


            val barIncrement = HISTORY_BAR_INCREMENT
            val parms = LinearLayout.LayoutParams(barIncrement)
        }
    }

}