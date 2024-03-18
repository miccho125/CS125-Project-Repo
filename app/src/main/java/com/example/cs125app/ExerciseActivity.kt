package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton


public class ExerciseActivity : ComponentActivity() {

    lateinit var buttonSleep: Button
    lateinit var buttonHome: Button
    lateinit var buttonDiet: Button

    lateinit var exerciseTextView: TextView
    lateinit var Aerobic: CheckBox
    lateinit var Anaerobic: CheckBox
    lateinit var Casual: CheckBox
    lateinit var exerciseDuration: EditText
    lateinit var caloriesConsumed: EditText
    lateinit var record: Button
    lateinit var input: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_exercise)

        buttonSleep = findViewById(R.id.buttonSleep)
        buttonHome = findViewById(R.id.buttonHome)
        buttonDiet = findViewById(R.id.buttonDiet)

        exerciseTextView = findViewById(R.id.textView2)
        Aerobic = findViewById(R.id.checkBox)
        Anaerobic = findViewById(R.id.checkBox2)
        Casual = findViewById(R.id.checkBox3)
        exerciseDuration = findViewById(R.id.editUserWeight1)
        caloriesConsumed = findViewById(R.id.editUserWeight2)
        record = findViewById(R.id.recordw)
        input = findViewById(R.id.importw)




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

        record.setOnClickListener {
            val exerciseDuration = exerciseDuration.text.toString().toIntOrNull()
            val caloriesConsumed = caloriesConsumed.text.toString().toIntOrNull()
            val exercisePoint = 0

            if (exerciseDuration != null && caloriesConsumed != null) {
                    if (exerciseDuration <= 5 || caloriesConsumed <= 10){
                        exerciseTextView.text = "Please Exercise More"
                    } else {
                        exercisePoint + exerciseDuration * 0.5 + caloriesConsumed
                        if(Aerobic.isChecked)
                        {
                            exercisePoint * 1.1
                        }
                        if(Anaerobic.isChecked)
                        {
                            exercisePoint * 1.3
                        }
                        if(Casual.isChecked)
                        {
                            exercisePoint * 0.8
                        }
                        exerciseTextView.text = "You Exercise Point is" + exercisePoint.toString()
                    }
            } else {
                exerciseTextView.text = "Please input your Exercise Data"
            }
        }

    }

}

