package com.example.bintinamoodtracker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.view.MotionEvent
import android.widget.EditText
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.y1
import com.example.bintinamoodtracker.myApp.Companion.y2
import kotlin.math.abs

///////////////////////////////////
//view finding
//////////////////////////////////


//////////////////////////////////
//onClick
//////////////////////////////////
/////////////////////////////////
//toActivityIntent
fun Activity.navigateTo (destinationActivity: Activity){
    val intent = Intent(this, destinationActivity::class.java)
    startActivity(intent)
}// this isn't working yet,haven't understood why yet
/////////////////////////////////


//////////////////////////////////
//dialogBuilder
//////////////////////////////////


//////////////////////////////////
//notificationMaker
//////////////////////////////////


//////////////////////////////////
//onScroll
//////////////////////////////////
