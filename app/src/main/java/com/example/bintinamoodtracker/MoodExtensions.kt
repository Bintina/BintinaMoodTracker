package com.example.bintinamoodtracker

import android.annotation.SuppressLint
import android.app.Activity
import android.app.AlarmManager
import android.app.Application
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.view.MotionEvent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.myApp.Companion.arrayOfBackgrounds
import com.example.bintinamoodtracker.myApp.Companion.arrayOfImages

import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.gestureDetector

import com.example.bintinamoodtracker.myApp.Companion.moodSharedPref
import com.example.bintinamoodtracker.myApp.Companion.y1
import com.example.bintinamoodtracker.myApp.Companion.y2
import com.google.gson.Gson
import kotlin.math.abs


///////////////////////////////////////////////////////////
// Shared preferences
//toJson
//savePref
/*fun objectToPreference(mood: Mood,PREFERENCE_NAME:String){
    moodSharedPref = getSharedPreferences(myApp.FILE_NAME, Application.MODE_PRIVATE)
    val moodSharedPrefEditor = moodSharedPref.edit()

    val moodJsonString = Gson().toJson(mood)

    moodSharedPrefEditor.putString(PREFERENCE_NAME,moodJsonString).apply()

}*/

//fromJson
//getPref
fun getPrefs (){
}
///////////////////////////////////////////////////////////


/////////////////////////////////////////////////////////////
//initializeMood
fun Activity.getLastMood(): Mood {
    val currentMoodString = myApp.moodSharedPref.getString(myApp.CURRENT_MOOD, null)


    if (currentMoodString != null) {
        myApp.currentMood = Gson().fromJson<Mood>(currentMoodString, Mood::class.java)
    } else {
        myApp.currentMood = Mood()
    }

    return myApp.currentMood
}


///////////////////////////////////////////////////////////

///////////////////////////////////////////////////////////
//viewMoodSetting
//emojies
/*fun Activity.setMoodEmoji(currentMood: Mood) {
    moodImage.setImageResource(myApp.arrayOfImages[currentMood.moodScore])
    background.setBackgroundColor(getColor(myApp.arrayOfBackgrounds[currentMood.moodScore]))

}*/

//historybars

///////////////////////////////////////////////////////////


//////////////////////////////////////////////////////////
//Trigger Alarm
fun Activity.triggerAlarm() {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val saveMoodRequestCode = 1001
    val intent = Intent(this, SaveReceiver::class.java)
    intent.action = "SAVE_DAILY_MOOD"

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
