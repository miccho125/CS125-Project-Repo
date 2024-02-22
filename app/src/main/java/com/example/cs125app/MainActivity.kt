package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup


public class MainActivity : ComponentActivity() {

    lateinit var username: EditText;
    lateinit var userHeightFt: NumberPicker;
    lateinit var userHeightIn: NumberPicker;
    lateinit var buttonGens: RadioGroup
    lateinit var userWeight: EditText
    lateinit var buttonSleep1: Button
    lateinit var buttonDiet1: Button
    lateinit var buttonExercise1: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.EditUserName)
        userHeightFt = findViewById(R.id.EditUserHeightFeet)
        userHeightIn = findViewById(R.id.EditUserHeightInch)
        buttonGens = findViewById(R.id.buttonsGender)
        userWeight = findViewById(R.id.EditUserWeight)
        buttonSleep1 = findViewById(R.id.buttonSleep1)
        buttonDiet1 = findViewById(R.id.buttonDiet1)
        buttonExercise1 = findViewById(R.id.buttonExercise1)

        buttonSleep1.setOnClickListener{
            startActivity(Intent(this, SleepActivity::class.java))
            finish()
        }
        buttonDiet1.setOnClickListener{
            startActivity(Intent(this, DietActivity::class.java))
            finish()
        }
        buttonExercise1.setOnClickListener{
            startActivity(Intent(this, ExerciseActivity::class.java))
            finish()
        }

        userHeightFt.setMaxValue(10)
        userHeightFt.setMinValue(0)

        userHeightIn.setMaxValue(11)
        userHeightIn.setMinValue(0)

        val settings = getSharedPreferences("UserInfo", 0)
        username.setText(settings.getString("username",""))
        buttonGens.check(settings.getInt("buttonGenders", -1))
        userHeightFt.setValue(settings.getInt("userFeet", 5))
        userHeightIn.setValue(settings.getInt("userInch", 0))
        userWeight.setText(settings.getString("userWeight", ""))
    }

    override fun onStop()
    {
        super.onStop()
        val settings = getSharedPreferences("UserInfo", 0)
        val edit = settings.edit()

        edit.putString("username",username.text.toString())
        edit.putInt("buttonGenders", buttonGens.checkedRadioButtonId)
        edit.putInt("userFeet", userHeightFt.value)
        edit.putInt("userInch", userHeightIn.value)
        edit.putString("userWeight", userWeight.text.toString())
        edit.apply()

    }
}

