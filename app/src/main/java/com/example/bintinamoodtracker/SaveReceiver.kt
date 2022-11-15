package com.example.bintinamoodtracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log
import com.example.bintinamoodtracker.myApp.Companion.CURRENT_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FIVE_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FOUR_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.SEVEN_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.SIX_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.THREE_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.TWO_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.defaultMoodObject
import com.example.bintinamoodtracker.myApp.Companion.fiveDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.fourDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.sevenDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.sixDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.threeDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.todayMood
import com.example.bintinamoodtracker.myApp.Companion.twoDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.yesterdayMood


class SaveReceiver : BroadcastReceiver() {

    override fun onReceive(context: Context, intent: Intent?) {
        val d = Log.d(
            "SaveAlarmLog",
            "Save Receiver Broadcast Received"
        )
        shuffleMoods(context)
        initialiseHistoryVariables(context)


    }

    fun shuffleMoods(context: Context): Context {
        var day0String = preferenceToJson(context, CURRENT_MOOD)
        var day1String = preferenceToJson(context, YESTERDAY_MOOD)
        var day2String = preferenceToJson(context, TWO_DAYS_AGO_MOOD)
        var day3String = preferenceToJson(context, THREE_DAYS_AGO_MOOD)
        var day4String = preferenceToJson(context, FOUR_DAYS_AGO_MOOD)
        var day5String = preferenceToJson(context, FIVE_DAYS_AGO_MOOD)
        var day6String = preferenceToJson(context, SIX_DAYS_AGO_MOOD)
        var day7String = preferenceToJson(context, SEVEN_DAYS_AGO_MOOD)

        Log.d(
            "JsonLog",

            "$day0String\n" +
                    "$day1String\n" +
                    "$day2String\n" +
                    "$day3String\n" +
                    "$day4String\n" +
                    "$day5String\n" +
                    "$day6String\n"
        )
        day7String = day6String
        day6String = day5String
        day5String = day4String
        day4String = day3String
        day3String = day2String
        day2String = day1String
        day1String = day0String

        val defaultMoodString = objectToJson(context, defaultMoodObject).toString()
        day0String = defaultMoodString

        Log.d(
            "defaultLog",
            "$defaultMoodString\n $day0String"
        )

        jsonToPreference(context, day0String, CURRENT_MOOD)
        jsonToPreference(context, day1String!!, YESTERDAY_MOOD)
        jsonToPreference(context, day2String!!, TWO_DAYS_AGO_MOOD)
        jsonToPreference(context, day3String!!, THREE_DAYS_AGO_MOOD)
        jsonToPreference(context, day4String!!, FOUR_DAYS_AGO_MOOD)
        jsonToPreference(context, day5String!!, FIVE_DAYS_AGO_MOOD)
        jsonToPreference(context, day6String!!, SIX_DAYS_AGO_MOOD)
        jsonToPreference(context, day7String!!, SEVEN_DAYS_AGO_MOOD)

        Log.d(
            "ShuffleLog",
            "Show me. Today: $todayMood, and $defaultMoodString" +
                    "\nYesterday: $yesterdayMood, and $day0String" +
                    "\n2Days: $twoDaysAgoMood, and $day1String" +
                    "\n3Days: $threeDaysAgoMood, and $day2String" +
                    "\n4Days: $fourDaysAgoMood, and $day3String" +
                    "\n5Days: $fiveDaysAgoMood, and $day4String" +
                    "\n6Days: $sixDaysAgoMood, and $day5String" +
                    "\n7Days: $sevenDaysAgoMood and $day6String"
        )

        currentMood = Mood()
        setMood()
        return context
    }
}
