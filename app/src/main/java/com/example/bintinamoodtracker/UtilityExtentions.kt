package com.example.bintinamoodtracker

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.util.Log
import android.view.MotionEvent
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
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
fun Activity.buildDialog(){
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
//////////////////////////////////


//////////////////////////////////
//notificationMaker
//////////////////////////////////


//////////////////////////////////
//onScroll
//////////////////////////////////
