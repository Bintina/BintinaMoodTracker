package com.example.bintinamoodtracker

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.Intent
import android.content.SharedPreferences
import android.util.Log
import android.widget.Toast
import com.example.bintinamoodtracker.myApp.Companion.CURRENT_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FILE_NAME
import com.example.bintinamoodtracker.myApp.Companion.YESTERDAY_MOOD
import com.google.gson.Gson


class SaveReceiver : BroadcastReceiver() {


    override fun onReceive(context: Context?, intent: Intent?) {
        Log.d(
            "SaveAlarmLog",
            "Save Receiver Broadcast Recieved"
        )




    }



}
