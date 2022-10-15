package com.example.bintinamoodtracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.dayMoodObject


class SaveReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val d = Log.d(
            "SaveAlarmLog",
            "Save Receiver Broadcast Recieved"
        )
        shuffleMoods(context)

    }
    fun shuffleMoods(context : Context):Context{
        myApp.sixDaysAgoMood = preferenceToObject(context, myApp.SIX_DAYS_AGO_MOOD)
        objectToPreference(context, myApp.sixDaysAgoMood, myApp.SEVEN_DAYS_AGO_MOOD)

        myApp.fiveDaysAgoMood = preferenceToObject(context, myApp.FIVE_DAYS_AGO_MOOD)
        objectToPreference(context, myApp.fiveDaysAgoMood, myApp.SIX_DAYS_AGO_MOOD)

        myApp.fourDaysAgoMood = preferenceToObject(context, myApp.FOUR_DAYS_AGO_MOOD)
        objectToPreference(context, myApp.fourDaysAgoMood, myApp.FIVE_DAYS_AGO_MOOD)

        myApp.threeDaysAgoMood =preferenceToObject(context, myApp.THREE_DAYS_AGO_MOOD)
        objectToPreference(context, myApp.threeDaysAgoMood, myApp.FOUR_DAYS_AGO_MOOD)

        myApp.twoDaysAgoMood =preferenceToObject(context, myApp.TWO_DAYS_AGO_MOOD)
        objectToPreference(context, myApp.twoDaysAgoMood, myApp.THREE_DAYS_AGO_MOOD)

        myApp.yesterdayMood =preferenceToObject(context, YESTERDAY_MOOD)
        objectToPreference(context, myApp.yesterdayMood, myApp.THREE_DAYS_AGO_MOOD)

        currentMood =preferenceToObject(context, myApp.CURRENT_MOOD)
        objectToPreference(context, currentMood, YESTERDAY_MOOD)
        return context
    }
}
