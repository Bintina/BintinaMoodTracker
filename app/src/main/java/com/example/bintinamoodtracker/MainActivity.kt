package com.example.bintinamoodtracker


import android.annotation.SuppressLint
import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.google.gson.Gson
import kotlin.math.abs

//Save last mood in shared preference

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {


    //Gesture variables
    lateinit var gestureDetector: GestureDetector
    var y1: Float = 0.0f
    var y2: Float = 0.0f

    companion object {
        const val MIN_DISTANCE = 150
    }

    lateinit var currentMood: Mood

    //MoodClass constructor variables
    val arrayOfMoods = arrayOf<String>(
        "Great Mood",
        "Good Mood",
        "Decent Mood",
        "Bad Mood",
        "Really Bad Mood"
    )
    val arrayOfBackgrounds = arrayOf<Int>(
        R.color.banana_yellow,
        R.color.light_sage,
        R.color.cornflower_blue_65,
        R.color.warm_grey,
        R.color.faded_red
    ).toIntArray()
    val arrayOfImages = arrayOf<Int>(
        R.drawable.smiley_super_happy,
        R.drawable.smiley_happy,
        R.drawable.smiley_normal,
        R.drawable.smiley_disappointed,
        R.drawable.smiley_sad
    ).toIntArray()

    //view initializing
    private lateinit var background: View
    private lateinit var moodImage: ImageView
    private lateinit var noteButton: ImageView
    private lateinit var historyActivity: ImageView

    //shared prefference and default MoodClass variables
    lateinit var moodSharedPref: SharedPreferences


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
        moodSharedPref = getSharedPreferences(myApp.FILE_NAME, MODE_PRIVATE)
        initialiseMood()
        //set MoodImage
        setMood()

        //setMoodViewElements on create
        noteButton.setOnClickListener {
            //Placeholder to insert a custom note
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

        historyActivity.setOnClickListener {
            val intent = Intent(this, HistoryActivity::class.java)
            startActivity(intent)
            //may want a back button to come back.
        }

            saveDailyMood()
    }

    private fun saveDailyMood() {
        val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager

        val saveMoodRequestCode = 1001
        val intent = Intent(this, SaveReceiver::class.java)
        intent.action = "SAVE_DAILY_MOOD"

        val alarmStartDelay = 5L
        val alarmIntervalInMillis = 60_000L
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
        Toast.makeText(this,"Daily mood save broadcast sent", Toast.LENGTH_LONG).show()
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
        moodImage.setImageResource(arrayOfImages[currentMood.moodScore])
        background.setBackgroundColor(getColor(arrayOfBackgrounds[currentMood.moodScore]))
    }


    //save mood object to Shared
    fun saveCommentAndMood() {

        val jsonMoodString = Gson().toJson(currentMood)

        moodSharedPref.edit().putString(myApp.CURRENT_MOOD, jsonMoodString).apply()

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

                if (abs(valueY) > MIN_DISTANCE) {
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


