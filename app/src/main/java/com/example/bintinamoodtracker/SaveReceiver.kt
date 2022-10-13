package com.example.bintinamoodtracker

import android.app.Application
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.myApp.Companion.CURRENT_MOOD
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.google.gson.Gson


class SaveReceiver : BroadcastReceiver() {

    lateinit var moodSharedPref: SharedPreferences

    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(
            "SaveAlarmLog",
            "Save Receiver Broadcast Recieved"
        )



    }



}
