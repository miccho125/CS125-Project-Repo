package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup


public class ExerciseActivity : ComponentActivity() {

    lateinit var buttonSleep: Button
    lateinit var buttonHome: Button
    lateinit var buttonDiet: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        buttonSleep = findViewById(R.id.buttonSleep)
        buttonHome = findViewById(R.id.buttonHome)
        buttonDiet = findViewById(R.id.buttonDiet)

        buttonSleep.setOnClickListener{
            startActivity(Intent(this, SleepActivity::class.java))
            finish()
        }
        buttonHome.setOnClickListener{
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
        buttonDiet.setOnClickListener{
            startActivity(Intent(this, DietActivity::class.java))
            finish()
        }

    }

}

