package com.example.bintinamoodtracker

import java.util.*

data class MoodDetail(val moodState: Enum<MoodType>,
                      val reason: String){
}

enum class MoodType(moodName: String,
                    moodEmoji: Int,
                    moodBackground: Int,
){
    greatMood("Great Mood",4,4),
    goodMood("Good Mood",3,3),
    decentMood("Decent Mood",2,2),
    badMood("Bad Mood",1,1),
    reallyBadMood("Really Bad Mood",0,0)
}
