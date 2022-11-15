package com.example.bintinamoodtracker

import android.app.Activity
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.util.Log
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.mainBackgroundView

//..................................................................................................
//Dialog Builder
fun Activity.buildDialog() {
    val builder = AlertDialog.Builder(this)
    val inflater = layoutInflater
    val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
    val moodReasonEt = dialogLayout.findViewById<EditText>(R.id.mood_reason_et)

    with(builder) {
        setTitle("Make a note")
        setPositiveButton("Save") { dialog, which ->
            currentMood.comment = moodReasonEt.text.toString()
        }
        setNegativeButton("Cancel") { dialog, which ->
            Log.d("Main", "Negative button clicked")
        }
        setView(dialogLayout)
        show()
    }
}
//..................................................................................................

//Trigger Alarm.....................................................................................
fun Activity.triggerAlarm() {
    val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

    val saveMoodRequestCode = 1001
    val intent = Intent(this, SaveReceiver::class.java)
    intent.action = "FOO"


    val alarmStartDelay = 5L
    val alarmIntervalInMillis = 20_000L
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
//..................................................................................................

//Set View Elements.................................................................................
//Activity Function
fun Activity.setMood() {
    myApp.mainImageView.setImageResource(myApp.arrayOfImages[currentMood.moodScore])
    mainBackgroundView.setBackgroundColor(getColor(myApp.arrayOfBackgrounds[currentMood.moodScore]))
}

//Broadcast Function
fun setMood() {
    myApp.mainImageView.setImageResource(myApp.arrayOfImages[currentMood.moodScore])
    myApp.mainBackgroundView.setBackgroundColor(myApp.arrayOfBackgrounds[currentMood.moodScore])
}

//..................................................................................................
