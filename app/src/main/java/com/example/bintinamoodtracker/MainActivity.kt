package com.example.bintinamoodtracker


import android.annotation.SuppressLint
import android.content.Intent
import android.content.SharedPreferences
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.view.GestureDetector
import android.view.LayoutInflater
import android.view.MotionEvent
import android.view.View
import android.widget.EditText
import android.widget.ImageView
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import kotlin.math.abs

//Save last mood in shared preference

class MainActivity : AppCompatActivity(), GestureDetector.OnGestureListener {

    lateinit var gestureDetector: GestureDetector
    var y1: Float = 0.0f
    var y2: Float = 0.0f

    companion object {
        const val MIN_DISTANCE = 150
    }

    var currentMoodScore: Int = 2

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

    private lateinit var background: View
    private lateinit var moodImage: ImageView
    private lateinit var noteButton: ImageView
    private lateinit var historyActivity: ImageView

    lateinit var moodSharedPref: SharedPreferences
    var lastMoodShared: Int = 2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        gestureDetector = GestureDetector(this, this)

        moodSharedPref = getSharedPreferences("LAST_MOOD", MODE_PRIVATE)
        val editorMoodSharedPref = moodSharedPref.edit()

        editorMoodSharedPref.apply(){
            putInt("lastMoodString",currentMoodScore)
            apply()
        }
        lastMoodShared = moodSharedPref.getInt("lastMoodString",currentMoodScore).toInt()


        background = findViewById(R.id.mood_container)
        moodImage = findViewById(R.id.emoji_image)
        noteButton = findViewById(R.id.custom_note)
        historyActivity = findViewById(R.id.mood_history)


        //set MoodImage

        setMood()

        //setMoodViewElements on create 2
        noteButton.setOnClickListener {
            //Placeholder to insert a custom note
            val builder = AlertDialog.Builder(this)
            val inflater = layoutInflater
            val dialogLayout = inflater.inflate(R.layout.dialog_layout, null)
            val moodReasonEt = dialogLayout.findViewById<EditText>(R.id.mood_reason_et)

            with(builder) {
                setTitle("Make a note")
                setPositiveButton("Save"){ dialog, which ->
                    val moodReason = moodReasonEt.text.toString()
                }
                setNegativeButton("Cancel"){dialog, which ->
                    Log.d("Main","Negative button clicked")
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

    }


    @RequiresApi(Build.VERSION_CODES.M)
    fun setMood() {
        moodImage.setImageResource(arrayOfImages[lastMoodShared])
        background.setBackgroundColor(getColor(arrayOfBackgrounds[lastMoodShared]))

        moodImage.setImageResource(arrayOfImages[currentMoodScore])
        background.setBackgroundColor(getColor(arrayOfBackgrounds[currentMoodScore]))


    }

    //fun SetMoodViewElements() {    }

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


                //TODO restructure so that 91 & 93 aren't required

                if (abs(valueY) > MIN_DISTANCE) {
                    //top to bottom swipe
                    if (currentMoodScore > 0) {
                        if (y2 > y1) {
                            currentMoodScore -= 1
                            println("current Mood score is $currentMoodScore")
                            setMood()
                        }
                    }
                    //bottom to top swipe
                    if (currentMoodScore < 4) {
                        if (y2 < y1) {
                            currentMoodScore += 1
                            println("current Mood score is $currentMoodScore")
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

}


