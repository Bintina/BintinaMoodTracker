package com.example.bintinamoodtracker

import android.app.Application
import android.content.BroadcastReceiver
import android.content.SharedPreferences
import com.google.gson.Gson

class myApp: Application() {
    private lateinit var moodSharedPref: SharedPreferences

    companion object {
        const val FILE_NAME = "Mood Preferences"
        const val CURRENT_MOOD = "Current Mood"
        const val YESTERDAY_MOOD = "Yesterdays Mood"
        const val TWO_DAYS_AGO_MOOD = "Two Days Ago Mood"
        const val THREE_DAYS_AGO_MOOD = "Three Days Ago Mood"
        const val FOUR_DAYS_AGO_MOOD = "Four Days Ago Mood"
        const val FIVE_DAYS_AGO_MOOD = "Five Days Ago Mood"
        const val SIX_DAYS_AGO_MOOD = "Six Days Ago Mood"
        const val SEVEN_DAYS_AGO_MOOD = "Seven Days Ago Mood"
    }

    lateinit var dayZeroMood: Mood
    lateinit var yesterdayMood: Mood
    lateinit var twoDaysAgoMood: Mood
    lateinit var threeDaysAgoMood: Mood
    lateinit var fourDaysAgoMood: Mood
    lateinit var fiveDaysAgoMood: Mood
    lateinit var sixDaysAgoMood: Mood

    val arrayOfMoods = arrayOf<String>(
        "Great Mood",
        "Good Mood",
        "Decent Mood",
        "Bad Mood",
        "Really Bad Mood"
    )

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

    var moodReason: String = "Still Nothing"


}