package com.example.bintinamoodtracker


import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.myApp.Companion.CURRENT_MOOD
import com.example.bintinamoodtracker.myApp.Companion.FILE_NAME
import com.example.bintinamoodtracker.myApp.Companion.currentMood
import com.example.bintinamoodtracker.myApp.Companion.gestureDetector
import com.example.bintinamoodtracker.myApp.Companion.moodSharedPref
import com.example.bintinamoodtracker.myApp.Companion.y1
import com.example.bintinamoodtracker.myApp.Companion.y2
import com.google.gson.Gson
import kotlin.math.abs


class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    //view initializing
    private lateinit var background: View
    private lateinit var moodImage: ImageView
    private lateinit var noteButton: ImageView
    private lateinit var historyActivity: ImageView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //gesture detect
        gestureDetector = GestureDetector(this, this)

        // Find views. view variable allocations
        background = findViewById(R.id.mood_container)
        moodImage = findViewById(R.id.emoji_image)
        noteButton = findViewById(R.id.custom_note)
        historyActivity = findViewById(R.id.mood_history)

        //Shared Preferences first attempt. It's intended to save the score
        moodSharedPref = getSharedPreferences(FILE_NAME, MODE_PRIVATE)
        initialiseMood()
        //set MoodImage
        setMood()

        //setMoodViewElements on create
        noteButton.setOnClickListener {
            buildDialog()

        }

        historyActivity.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            //may want a back button to come back.
        }
        triggerAlarm()
    }

    private fun initialiseMood() {
        val currentMoodString = moodSharedPref.getString(myApp.CURRENT_MOOD, null)

        if (currentMoodString != null) {
            currentMood = Gson().fromJson<Mood>(currentMoodString, Mood::class.java)
        } else {
            currentMood = Mood()
        }
    }

    fun setMood() {
        moodImage.setImageResource(myApp.arrayOfImages[currentMood.moodScore])
        background.setBackgroundColor(getColor(myApp.arrayOfBackgrounds[currentMood.moodScore]))
    }

    //save mood object to Shared
    fun saveCommentAndMood() {
        objectToPreference(this,currentMood, CURRENT_MOOD)
    }

    //**//GestureDetector
    @SuppressLint("NewApi")
    override fun onTouchEvent(event: MotionEvent?): Boolean {

        gestureDetector.onTouchEvent(event)



        when (event?.action) {

            0 -> {
                y1 = event.y
            }

            1 -> {
                y2 = event.y

                val valueY: Float = y2 - y1

                if (abs(valueY) > myApp.MIN_DISTANCE) {
                    //top to bottom swipe
                    if (currentMood.moodScore > 0) {
                        if (y2 > y1) {
                            currentMood.moodScore -= 1
                            println("current Mood score is $currentMood.moodScore")
                            setMood()
                        }
                    }
                    //bottom to top swipe
                    if (currentMood.moodScore < 4) {
                        if (y2 < y1) {
                            currentMood.moodScore += 1
                            println("current Mood score is $currentMood.moodScore")
                            setMood()
                        }
                    }

                }
            }
        }
        return super.onTouchEvent(event)
    }


    override fun onDown(e: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onShowPress(e: MotionEvent?) {
        //TODO("Not yet implemented")
    }

    override fun onSingleTapUp(e: MotionEvent?): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onScroll(
        e1: MotionEvent?,
        e2: MotionEvent?,
        distanceX: Float,
        distanceY: Float
    ): Boolean {
        //TODO("Not yet implemented")
        return false
    }

    override fun onLongPress(e: MotionEvent?) {
        // TODO("Not yet implemented")
    }

    override fun onFling(
        e1: MotionEvent?,
        e2: MotionEvent?,
        velocityX: Float,
        velocityY: Float
    ): Boolean {
        // TODO("Not yet implemented")
        return false
    }

    override fun onStop() {
        super.onStop()

        saveCommentAndMood()
    }
}


