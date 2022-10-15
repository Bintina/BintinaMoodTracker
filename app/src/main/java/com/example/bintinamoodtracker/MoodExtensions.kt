package com.example.bintinamoodtracker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.myApp.Companion.CURRENT_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FILE_NAME
import com.example.bintinamoodtracker.myApp.Companion.FIVE_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FOUR_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.MIN_DISTANCE
import com.example.bintinamoodtracker.myApp.Companion.SEVEN_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.SIX_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.THREE_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.TWO_DAYS_AGO_MOOD
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.dayMoodObject
import com.example.bintinamoodtracker.myApp.Companion.fiveDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.fourDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.gestureDetector
import com.example.bintinamoodtracker.myApp.Companion.moodSharedPref
import com.example.bintinamoodtracker.myApp.Companion.sixDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.threeDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.twoDaysAgoMood
import com.example.bintinamoodtracker.myApp.Companion.y1
import com.example.bintinamoodtracker.myApp.Companion.y2
import com.example.bintinamoodtracker.myApp.Companion.yesterdayMood
import com.google.gson.Gson


///////////////////////////////////////////////////////////
// Shared preferences

//savePref
fun objectToPreference(context: Context, mood: Mood,PREFERENCE_NAME:String){
    moodSharedPref = context.getSharedPreferences(FILE_NAME, MODE_PRIVATE)
    val moodSharedPrefEditor = moodSharedPref.edit()

    val moodJsonString = Gson().toJson(mood)

    moodSharedPrefEditor.putString(PREFERENCE_NAME,moodJsonString).apply()

}


//getPref
fun preferenceToObject(context: Context, MOOD_DAY_PREFERENCE: String ): Mood {
    moodSharedPref = context.getSharedPreferences(FILE_NAME, AppCompatActivity.MODE_PRIVATE)


    val moodJsonString = moodSharedPref.getString(MOOD_DAY_PREFERENCE, null)
    dayMoodObject = Gson().fromJson<Mood>(moodJsonString, Mood::class.java)

    return dayMoodObject
}
///////////////////////////////////////////////////////////


///////////////////////////////////////////////////////////
//viewMoodSetting
//emojies
/*fun Activity.setMoodEmoji(currentMood: Mood) {
    moodImage.setImageResource(myApp.arrayOfImages[currentMood.moodScore])
    background.setBackgroundColor(getColor(myApp.arrayOfBackgrounds[currentMood.moodScore]))

}*/
//moodShufflecontext

//historybars
/*fun shuffleMoods(context : Context):Context{
    sixDaysAgoMood = preferenceToObject(context, SIX_DAYS_AGO_MOOD)
    objectToPreference(context, sixDaysAgoMood, SEVEN_DAYS_AGO_MOOD)

    fiveDaysAgoMood = preferenceToObject(context, FIVE_DAYS_AGO_MOOD)
    objectToPreference(context, fiveDaysAgoMood, SIX_DAYS_AGO_MOOD)

    fourDaysAgoMood = preferenceToObject(context, FOUR_DAYS_AGO_MOOD)
    objectToPreference(context, fourDaysAgoMood, FIVE_DAYS_AGO_MOOD)

    threeDaysAgoMood =preferenceToObject(context, THREE_DAYS_AGO_MOOD)
    objectToPreference(context, threeDaysAgoMood, FOUR_DAYS_AGO_MOOD)

    twoDaysAgoMood =preferenceToObject(context, TWO_DAYS_AGO_MOOD)
    objectToPreference(context, twoDaysAgoMood, THREE_DAYS_AGO_MOOD)

    yesterdayMood =preferenceToObject(context, YESTERDAY_MOOD)
    objectToPreference(context, yesterdayMood, THREE_DAYS_AGO_MOOD)

    currentMood =preferenceToObject(context, CURRENT_MOOD)
    objectToPreference(context, currentMood, YESTERDAY_MOOD)
 return context
}*/
///////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////
//Trigger Alarm
fun Activity.triggerAlarm() {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val saveMoodRequestCode = 1001
    val intent = Intent(this, SaveReceiver::class.java)
    intent.action = "FOO"


    val alarmStartDelay = 5L
    val alarmIntervalInMillis = 600_000L
    val alarmManagerTriggerTimeInMillis = System.currentTimeMillis() + alarmStartDelay * 1_000L
    val pendingIntent = PendingIntent.getBroadcast(
        this,
        saveMoodRequestCode,
        intent,
        PendingIntent.FLAG_IMMUTABLE
    )

    alarmManager.setRepeating(
        AlarmManager.RTC_WAKEUP,
        alarmManagerTriggerTimeInMillis,
        alarmIntervalInMillis,
        pendingIntent
    )

    Toast.makeText(this, "Daily mood save broadcast sent", Toast.LENGTH_LONG).show()
}
//////////////////////////////////////////////////////////
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

///////////////////////////////////////////////
//Mood Scrolling Attempt
/*
@SuppressLint("NewApi")
override fun Activity.onTouchEvent(event: MotionEvent?): Boolean {

    gestureDetector.onTouchEvent(event)



    when (event?.action) {

        0 -> {
            y1 = event.y
        }

        1 -> {
            y2 = event.y

            val valueY: Float = y2 - y1

            if (abs(valueY) > MainActivity.MIN_DISTANCE) {
                //top to bottom swipe
                if (currentMood.moodScore > 0) {
                    if (y2 > y1) {
                        currentMood.moodScore -= 1
                        println("current Mood score is $currentMood.moodScore")
                        setMoodEmoji(currentMood)
                    }
                }
                //bottom to top swipe
                if (currentMood.moodScore < 4) {
                    if (y2 < y1) {
                        currentMood.moodScore += 1
                        println("current Mood score is $currentMood.moodScore")
                        setMoodEmoji(currentMood)
                    }
                }

            }
        }
    }
    return super.onTouchEvent(event)
}


override fun Activity.onDown(e: MotionEvent?): Boolean {
    //TODO("Not yet implemented")
    return false
}

override fun Activity.onShowPress(e: MotionEvent?) {
    //TODO("Not yet implemented")
}

override fun Activity.onSingleTapUp(e: MotionEvent?): Boolean {
    //TODO("Not yet implemented")
    return false
}

override fun Activity.onScroll(
    e1: MotionEvent?,
    e2: MotionEvent?,
    distanceX: Float,
    distanceY: Float
): Boolean {
    //TODO("Not yet implemented")
    return false
}

override fun Activity.onLongPress(e: MotionEvent?) {
    // TODO("Not yet implemented")
}

override fun Activity.onFling(
    e1: MotionEvent?,
    e2: MotionEvent?,
    velocityX: Float,
    velocityY: Float
): Boolean {
    // TODO("Not yet implemented")
    return false
}

override fun Activity.onStop() {
    super.onStop()

    saveCommentAndMood()
}
*/

//////////////////////////////////////////////
