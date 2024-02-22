package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup


public class SleepActivity : ComponentActivity() {

    lateinit var buttonDiet: Button
    lateinit var buttonHome: Button
    lateinit var buttonExercise: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)

        buttonDiet = findViewById(R.id.buttonDiet)
        buttonHome = findViewById(R.id.buttonHome)
        buttonExercise = findViewById(R.id.buttonExercise)

        buttonDiet.setOnClickListener{
            startActivity(Intent(this, DietActivity::class.java))
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

