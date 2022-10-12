package com.example.bintinamoodtracker

import android.content.SharedPreferences
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.bintinamoodtracker.databinding.ActivityHistoryBinding
import com.google.gson.Gson

class HistoryActivity : AppCompatActivity() {

    lateinit var historyBinding: ActivityHistoryBinding


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

    var moodReason: String = "Still Nothing"

    //shared prefference and default MoodClass variables
    private lateinit var moodSharedPref: SharedPreferences

    lateinit var dayZeroMood: Mood
    lateinit var yesterdayMood: Mood
    lateinit var twoDaysAgoMood: Mood
    lateinit var threeDaysAgoMood: Mood
    lateinit var fourDaysAgoMood: Mood
    lateinit var fiveDaysAgoMood: Mood
    lateinit var sixDaysAgoMood: Mood

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        historyBinding = ActivityHistoryBinding.inflate(layoutInflater)
        setContentView(historyBinding.root)


        getMood()
        setViewElements()


        //getViewElements()
    }

    fun getMood() {
        //getting current mood
        moodSharedPref = getSharedPreferences(myApp.FILE_NAME, MODE_PRIVATE)
        val dayZeroMoodJsonString = moodSharedPref.getString(myApp.CURRENT_MOOD, null)
        dayZeroMood = Gson().fromJson<Mood>(dayZeroMoodJsonString, Mood::class.java)

//getting yesterdays mood
     /*  val yesterdayMoodString = moodSharedPref.getString(myApp.YESTERDAY_MOOD, null)
        yesterdayMood =Gson().fromJson(yesterdayMoodString,Mood::class.java)
*/

//getting two days ago mood
//getting three days ago mood
//getting four days ago mood
//getting five days ago mood
//getting six days ago mood
//getting seven days ago mood

    }


    //retrieve view elements from saved mood, to set view elements with
    fun setViewElements() {


        historyBinding.today.setBackgroundColor(getColor(arrayOfBackgrounds[dayZeroMood.moodScore]))
        historyBinding.today.text = dayZeroMood.comment.toString()

  /*      historyBinding.yesterday.setBackgroundColor(getColor(arrayOfBackgrounds[yesterdayMood.moodScore]))
        historyBinding.yesterday.text = yesterdayMood.comment.toString()
  */
       /* historyBinding.threeDays.setBackgroundColor(getColor(arrayOfBackgrounds[twoDaysAgoMood.moodScore]))
        historyBinding.threeDays.text = twoDaysAgoMood.comment.toString()

        historyBinding.fourDays.setBackgroundColor(getColor(arrayOfBackgrounds[threeDaysAgoMood.moodScore]))
        historyBinding.fourDays.text = threeDaysAgoMood.comment.toString()

        historyBinding.fiveDays.setBackgroundColor(getColor(arrayOfBackgrounds[fourDaysAgoMood.moodScore]))
        historyBinding.fiveDays.text = fourDaysAgoMood.comment.toString()

        historyBinding.sixDays.setBackgroundColor(getColor(arrayOfBackgrounds[fiveDaysAgoMood.moodScore]))
        historyBinding.sixDays.text = fiveDaysAgoMood.comment.toString()

        historyBinding.sevenDays.setBackgroundColor(getColor(arrayOfBackgrounds[sixDaysAgoMood.moodScore]))
        historyBinding.sevenDays.text = sixDaysAgoMood.comment.toString()

*/
    }

}