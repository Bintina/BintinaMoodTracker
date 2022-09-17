package com.example.bintinamoodtracker

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class HistoryActivity : AppCompatActivity() {

    private lateinit var oneWeekAgoV: View
    private lateinit var sixDaysAgoV: View
    private lateinit var fiveDaysAgoV: View
    private lateinit var fourDaysAgoV: View
    private lateinit var threeDaysAgoV: View
    private lateinit var twoDaysAgoV: View
    private lateinit var oneDayAgoV: View

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_history)

        oneWeekAgoV = findViewById(R.id.seven_days)
        sixDaysAgoV = findViewById(R.id.six_days)
        fiveDaysAgoV = findViewById(R.id.five_days)
        fourDaysAgoV = findViewById(R.id.four_days)
        threeDaysAgoV = findViewById(R.id.three_days)
        twoDaysAgoV = findViewById(R.id.yesterday)
        oneDayAgoV = findViewById(R.id.today)


    }
}