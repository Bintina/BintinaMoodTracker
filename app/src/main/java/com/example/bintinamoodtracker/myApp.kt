package com.example.bintinamoodtracker

import android.app.Activity
import android.app.Application
import android.content.SharedPreferences
import android.view.GestureDetector
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson

class myApp : Application() {

    companion object {


        lateinit var gestureDetector: GestureDetector
        var y1: Float = 0.0f
        var y2: Float = 0.0f

        const val MIN_DISTANCE = 150

        lateinit var currentMood: Mood
        lateinit var moodSharedPref: SharedPreferences
        const val FILE_NAME = "Mood Preferences"
        const val CURRENT_MOOD = "Current Mood"
        const val YESTERDAY_MOOD = "Yesterdays Mood"
        const val TWO_DAYS_AGO_MOOD = "Two Days Ago Mood"
        const val THREE_DAYS_AGO_MOOD = "Three Days Ago Mood"
        const val FOUR_DAYS_AGO_MOOD = "Four Days Ago Mood"
        const val FIVE_DAYS_AGO_MOOD = "Five Days Ago Mood"
        const val SIX_DAYS_AGO_MOOD = "Six Days Ago Mood"
        const val SEVEN_DAYS_AGO_MOOD = "Seven Days Ago Mood"

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
        var dayMoodObject: Mood =Mood()

        var yesterdayMood: Mood = Mood()
        var twoDaysAgoMood: Mood = Mood()
        var threeDaysAgoMood: Mood = Mood()
        var fourDaysAgoMood: Mood = Mood()
        var fiveDaysAgoMood: Mood = Mood()
        var sixDaysAgoMood: Mood = Mood()


    }


    fun preferenceToObject(MOOD_DAY_PREFERENCE: String ): Mood {
        moodSharedPref = getSharedPreferences(FILE_NAME, AppCompatActivity.MODE_PRIVATE)

        val moodJsonString = moodSharedPref.getString(MOOD_DAY_PREFERENCE, null)
        dayMoodObject = Gson().fromJson<Mood>(moodJsonString, Mood::class.java)

       return dayMoodObject
    }

    fun objectToPreference(mood: Mood,PREFERENCE_NAME:String){
        moodSharedPref = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        val moodSharedPrefEditor = moodSharedPref.edit()

        val moodJsonString = Gson().toJson(mood)

        moodSharedPrefEditor.putString(PREFERENCE_NAME,moodJsonString).apply()

    }
    fun shuffleMoods(){
        sixDaysAgoMood = preferenceToObject(SIX_DAYS_AGO_MOOD)
        objectToPreference(sixDaysAgoMood, SEVEN_DAYS_AGO_MOOD)

        fiveDaysAgoMood = preferenceToObject(FIVE_DAYS_AGO_MOOD)
        objectToPreference(fiveDaysAgoMood, SIX_DAYS_AGO_MOOD)

        fourDaysAgoMood = preferenceToObject(FOUR_DAYS_AGO_MOOD)
        objectToPreference(fourDaysAgoMood, FIVE_DAYS_AGO_MOOD)

        threeDaysAgoMood =preferenceToObject(THREE_DAYS_AGO_MOOD)
        objectToPreference(threeDaysAgoMood, FOUR_DAYS_AGO_MOOD)

        twoDaysAgoMood =preferenceToObject(TWO_DAYS_AGO_MOOD)
        objectToPreference(twoDaysAgoMood, THREE_DAYS_AGO_MOOD)

        yesterdayMood =preferenceToObject(YESTERDAY_MOOD)
        objectToPreference(yesterdayMood, THREE_DAYS_AGO_MOOD)

        currentMood =preferenceToObject(CURRENT_MOOD)
        objectToPreference(currentMood, YESTERDAY_MOOD)

    }

}