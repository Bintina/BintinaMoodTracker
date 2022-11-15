package com.example.bintinamoodtracker

import android.content.Context
import android.content.Context.MODE_PRIVATE
import com.example.bintinamoodtracker.myApp.Companion.CURRENT_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FILE_NAME
import com.example.bintinamoodtracker.myApp.Companion.FIVE_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FOUR_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.SEVEN_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.SIX_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.THREE_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.TWO_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.example.bintinamoodtracker.myApp.Companion.dayMoodObject
import com.example.bintinamoodtracker.myApp.Companion.defaultMoodObject
import com.example.bintinamoodtracker.myApp.Companion.fiveDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.fourDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.moodJsonString
import com.example.bintinamoodtracker.myApp.Companion.moodSharedPref
import com.example.bintinamoodtracker.myApp.Companion.sevenDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.sixDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.threeDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.todayMood
import com.example.bintinamoodtracker.myApp.Companion.twoDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.yesterdayMood
import com.google.gson.Gson


//..................................................................................................
//SharedPreference Methods
//------>>Pref......................................................................................
fun objectToPreference(context: Context, mood: Mood, PREFERENCE_NAME: String) {

    moodJsonString = objectToJson(context, mood)

    jsonToPreference(context, moodJsonString, PREFERENCE_NAME)
}

fun objectToJson(context: Context, mood: Mood): String {
    val moodJsonString = Gson().toJson(mood)

    return moodJsonString
}

fun jsonToPreference(context: Context, moodJsonString: String, PREFERENCE_NAME: String) {
    moodSharedPref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    val moodSharedPrefEditor = moodSharedPref.edit()

    moodSharedPrefEditor.putString(PREFERENCE_NAME, moodJsonString).apply()
}

//------>>Object....................................................................................
fun preferenceToObject(context: Context,PREFERENCE_NAME: String):Mood {
moodJsonString = preferenceToJson(context,PREFERENCE_NAME)
    dayMoodObject = jsonToObject(context, moodJsonString)

    return dayMoodObject
}

fun preferenceToJson(context: Context, PREFERENCE_NAME: String): String {
    moodSharedPref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)

    moodJsonString = moodSharedPref.getString(PREFERENCE_NAME, "").toString()

    val defaultMoodString = objectToJson(context, defaultMoodObject)

    if (moodJsonString.isNullOrEmpty()) {
        return defaultMoodString
    } else {
        return moodJsonString
    }
}

fun jsonToObject(context: Context,moodJsonString: String): Mood {

    dayMoodObject = Gson().fromJson<Mood>(moodJsonString, Mood::class.java)

    return dayMoodObject

}

//..................................................................................................
//initialitions
fun initialiseHistoryVariables(context: Context) {
    todayMood = preferenceToObject(context, CURRENT_MOOD)
    yesterdayMood = preferenceToObject(context, YESTERDAY_MOOD)
    twoDaysAgoMood = preferenceToObject(context, TWO_DAYS_AGO_MOOD)
    threeDaysAgoMood = preferenceToObject(context, THREE_DAYS_AGO_MOOD)
    fourDaysAgoMood = preferenceToObject(context, FOUR_DAYS_AGO_MOOD)
    fiveDaysAgoMood = preferenceToObject(context, FIVE_DAYS_AGO_MOOD)
    sixDaysAgoMood = preferenceToObject(context, SIX_DAYS_AGO_MOOD)
    sevenDaysAgoMood = preferenceToObject(context, SEVEN_DAYS_AGO_MOOD)
}
