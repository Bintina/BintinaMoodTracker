package com.example.bintinamoodtracker

import android.app.Activity
import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import android.view.GestureDetector
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlin.properties.Delegates

class myApp : Application() {

    companion object {

        lateinit var gestureDetector: GestureDetector
        var y1: Float = 0.0f
        var y2: Float = 0.0f

        //Measurements
        const val MIN_DISTANCE = 150
        var SCREEN_WIDTH by Delegates.notNull<Double>()
        var HISTORY_BAR_INCREMENT by Delegates.notNull<Double>()

        lateinit var currentMood: Mood
        lateinit var moodSharedPref: SharedPreferences
        lateinit var moodJsonString: String
        const val FILE_NAME = "Mood Preferences"
        const val CURRENT_MOOD = "Current Mood"
        const val YESTERDAY_MOOD = "Yesterdays Mood"
        const val TWO_DAYS_AGO_MOOD = "Two Days Ago Mood"
        const val THREE_DAYS_AGO_MOOD = "Three Days Ago Mood"
        const val FOUR_DAYS_AGO_MOOD = "Four Days Ago Mood"
        const val FIVE_DAYS_AGO_MOOD = "Five Days Ago Mood"
        const val SIX_DAYS_AGO_MOOD = "Six Days Ago Mood"
        const val SEVEN_DAYS_AGO_MOOD = "Seven Days Ago Mood"

        //View constants
        lateinit var mainBackgroundView: android.view.View
        lateinit var mainImageView: ImageView

        val arrayOfBackgrounds = arrayOf<Int>(
            R.color.banana_yellow,
            R.color.light_sage,
            R.color.cornflower_blue_65,
            R.color.warm_grey,
            R.color.faded_red
        ).toIntArray()
        val arrayOfImages = arrayOf<Int>(
            R.drawable.smiley_super_happy,
            R.drawable.smiley_happy,
            R.drawable.smiley_normal,
            R.drawable.smiley_disappointed,
            R.drawable.smiley_sad
        ).toIntArray()

        //History variables

        val defaultMoodObject = Mood()
        var dayMoodObject: Mood =Mood()

        var todayMood:Mood = Mood()
        var yesterdayMood: Mood = Mood()
        var twoDaysAgoMood: Mood = Mood()
        var threeDaysAgoMood: Mood = Mood()
        var fourDaysAgoMood: Mood = Mood()
        var fiveDaysAgoMood: Mood = Mood()
        var sixDaysAgoMood: Mood = Mood()
        var sevenDaysAgoMood: Mood = Mood()

    }



}