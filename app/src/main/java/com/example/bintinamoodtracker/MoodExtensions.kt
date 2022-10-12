package com.example.bintinamoodtracker

import android.app.Application
import android.content.BroadcastReceiver
import com.google.gson.Gson


///////////////////////////////////////////////////////////
// Shared preferences
//toJson
//savePref
//fromJson
//getPref
///////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////
//initializeMood
///////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
//viewMoodSetting
//emojies
//historybars
///////////////////////////////////////////////////////////

/* saveMoodHistoryAttempt
private fun BroadcastReceiver.saveMoodHistory() {

    moodSharedPref = getSharedPreferences(myApp.FILE_NAME, Application.MODE_PRIVATE)

    //saving todays mood to yesterday
    val currentMoodString = moodSharedPref.getString(myApp.CURRENT_MOOD, null)
    val todayMoodString = Gson().fromJson<Mood>(currentMoodString, Mood::class.java)

    val editSharedPreferences = moodSharedPref.edit()
    val yesterdayGsonString = Gson().toJson(todayMoodString)
    editSharedPreferences.putString(myApp.YESTERDAY_MOOD, yesterdayGsonString)

    //saving yesterdays mood to two days ago
    //saving two days ago mood to three days ago
    //saving three days ago mood to four days ago
    //saving four days ago mood to five days ago
    //saving five days ago mood to six days ago
    //saving six days ago mood to seven days ago


}*/
