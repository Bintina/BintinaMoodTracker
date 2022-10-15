package com.example.bintinamoodtracker

import android.app.Activity
import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.databinding.ActivityHistoryBinding
import com.example.bintinamoodtracker.myApp.Companion.arrayOfBackgrounds
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.fiveDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.fourDaysAgoMood
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

        setViewElements()


    }

    fun setViewElements() {


        historyBinding.today.setBackgroundColor(getColor(arrayOfBackgrounds[currentMood.moodScore]))
        historyBinding.today.text = currentMood.comment.toString()

        historyBinding.yesterday.setBackgroundColor(getColor(arrayOfBackgrounds[yesterdayMood.moodScore]))
        historyBinding.yesterday.text = yesterdayMood.comment.toString()

        historyBinding.threeDays.setBackgroundColor(getColor(arrayOfBackgrounds[twoDaysAgoMood.moodScore]))
        historyBinding.threeDays.text = twoDaysAgoMood.comment.toString()

        historyBinding.fourDays.setBackgroundColor(getColor(arrayOfBackgrounds[threeDaysAgoMood.moodScore]))
        historyBinding.fourDays.text = threeDaysAgoMood.comment.toString()

        historyBinding.fiveDays.setBackgroundColor(getColor(arrayOfBackgrounds[fourDaysAgoMood.moodScore]))
        historyBinding.fiveDays.text = fourDaysAgoMood.comment.toString()

        historyBinding.sixDays.setBackgroundColor(getColor(arrayOfBackgrounds[fiveDaysAgoMood.moodScore]))
        historyBinding.sixDays.text = fiveDaysAgoMood.comment.toString()

        historyBinding.sevenDays.setBackgroundColor(getColor(arrayOfBackgrounds[sixDaysAgoMood.moodScore]))
        historyBinding.sevenDays.text = sixDaysAgoMood.comment.toString()

    }

/*
    fun shuffleMoods() {
        myApp.sixDaysAgoMood = preferenceToObject(myApp.SIX_DAYS_AGO_MOOD)
        objectToPreference(myApp.sixDaysAgoMood, myApp.SEVEN_DAYS_AGO_MOOD)

        myApp.fiveDaysAgoMood = preferenceToObject(myApp.FIVE_DAYS_AGO_MOOD)
        objectToPreference(myApp.fiveDaysAgoMood, myApp.SIX_DAYS_AGO_MOOD)

        myApp.fourDaysAgoMood = preferenceToObject(myApp.FOUR_DAYS_AGO_MOOD)
        objectToPreference(myApp.fourDaysAgoMood, myApp.FIVE_DAYS_AGO_MOOD)

        myApp.threeDaysAgoMood = preferenceToObject(myApp.THREE_DAYS_AGO_MOOD)
        objectToPreference(myApp.threeDaysAgoMood, myApp.FOUR_DAYS_AGO_MOOD)

        myApp.twoDaysAgoMood = preferenceToObject(myApp.TWO_DAYS_AGO_MOOD)
        objectToPreference(myApp.twoDaysAgoMood, myApp.THREE_DAYS_AGO_MOOD)

        myApp.yesterdayMood = preferenceToObject(myApp.YESTERDAY_MOOD)
        objectToPreference(myApp.yesterdayMood, myApp.THREE_DAYS_AGO_MOOD)

        currentMood = preferenceToObject(myApp.CURRENT_MOOD)
        objectToPreference(currentMood, myApp.YESTERDAY_MOOD)

    }
*/
}