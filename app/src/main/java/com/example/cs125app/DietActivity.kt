package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup
import android.widget.TextView


public class DietActivity : ComponentActivity() {

    lateinit var buttonSleep: Button
    lateinit var buttonHome: Button
    lateinit var buttonExercise: Button
    lateinit var kCALS: TextView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)

        buttonSleep = findViewById(R.id.buttonSleep)
        buttonHome = findViewById(R.id.buttonHome)
        buttonExercise = findViewById(R.id.buttonExercise)
        kCALS = findViewById(R.id.textView2)
        val settings = getSharedPreferences("UserInfo", 0)
        var kCals = 0.0
        var height = (settings.getInt("userFeet", 0) * 12) + settings.getInt("userInch", 0)
        val weight = settings.getString("userWeight", "")
        var w = 0
        var age = 21
        if (weight != null)
        {
            w = weight.toInt()
        }
        if (settings.getInt("userGender", -1) == 0)
        {
            kCals = 66.0 + (6.2*w) + (12.7*height) - (6.76-age)
        }
        else if (settings.getInt("userGender", -1) == 1)
        {
            kCals = 655.1 + (4.35*w) + (4.7*height) - (4.7-age)
        }
        val t = settings.getInt("userGender", -1)
        val str = "KCal Eaten Today 0 / $kCals"
        kCALS.setText(str)

        buttonSleep.setOnClickListener{
            startActivity(Intent(this, SleepActivity::class.java))
            finish()
        }
        buttonHome.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        buttonExercise.setOnClickListener{
            startActivity(Intent(this, ExerciseActivity::class.java))
            finish()
        }

    }

}

