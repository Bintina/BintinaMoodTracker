package com.example.bintinamoodtracker

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.databinding.ActivityHistoryBinding
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.example.bintinamoodtracker.myApp.Companion.arrayOfBackgrounds
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.yesterdayMood
import com.google.gson.Gson

class HistoryActivity : AppCompatActivity() {

    lateinit var historyBinding: ActivityHistoryBinding

    val arrayOfBackgrounds = arrayOf<Int>(
        R.color.banana_yellow,
        R.color.light_sage,
        R.color.cornflower_blue_65,
        R.color.warm_grey,
        R.color.faded_red
    ).toIntArray()


    var moodReason: String = "Still Nothing"

    //shared prefference and default MoodClass variable values
    private lateinit var moodSharedPref: SharedPreferences

     var dayZeroMood: Mood = Mood()
     var yesterdayMood: Mood = Mood()
     var twoDaysAgoMood: Mood = Mood()
     var threeDaysAgoMood: Mood = Mood()
     var fourDaysAgoMood: Mood = Mood()
     var fiveDaysAgoMood: Mood = Mood()
     var sixDaysAgoMood: Mood = Mood()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding.root)


        getMood()
        setViewElements()
        //getViewElements()

        saveMoodAlarm()

    }

    private fun saveMoodAlarm() {
        triggerAlarm()


    }
    fun getMood() {
        //getting current mood
        moodSharedPref = getSharedPreferences(myApp.FILE_NAME, MODE_PRIVATE)
        val dayZeroMoodJsonString = moodSharedPref.getString(myApp.CURRENT_MOOD, null)
        dayZeroMood = Gson().fromJson<Mood>(dayZeroMoodJsonString, Mood::class.java)


    }




    //retrieve view elements from saved mood, to set view elements with
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

}