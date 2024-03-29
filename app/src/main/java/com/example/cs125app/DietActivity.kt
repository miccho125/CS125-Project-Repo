package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.ProgressBar
import android.widget.RadioGroup
import android.widget.TextView
import com.google.android.material.floatingactionbutton.FloatingActionButton
import java.text.DateFormat
import java.util.Calendar


public class DietActivity : ComponentActivity() {

    lateinit var buttonSleep: Button
    lateinit var buttonHome: Button
    lateinit var buttonExercise: Button
    lateinit var kCALS: TextView
    lateinit var water: TextView
    lateinit var kCalBar: ProgressBar
    lateinit var waterBar: ProgressBar
    lateinit var addMealSwitch: Button
    lateinit var CB0: CheckBox
    lateinit var CB2: CheckBox
    lateinit var CB3: CheckBox
    lateinit var CB4: CheckBox
    lateinit var CB6: CheckBox
    lateinit var CB7: CheckBox
    lateinit var CB8: FloatingActionButton
    lateinit var CB9: CheckBox
    lateinit var CB10: CheckBox
    lateinit var CText: TextView
    lateinit var WText: TextView
    lateinit var buttonMealSearch: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_diet)
        val settings = getSharedPreferences("UserInfo", 0)
        val calender = Calendar.getInstance().time
        val dateForm = DateFormat.getDateInstance().format(calender)
        val dateTV = findViewById<TextView>(R.id.DaysDate)
        dateTV.text = dateForm
        if(settings.getString("date", null) != dateForm)
        {
            val edit = settings.edit()
            edit.putString("date", dateForm)
            edit.putInt("kCals", 0)
            edit.putInt("water", 0)
            edit.putBoolean("GEaten", false)
            edit.putBoolean("FEaten", false)
            edit.putBoolean("VEaten", false)
            edit.putBoolean("PEaten", false)
            edit.apply()
        }

        buttonSleep = findViewById(R.id.buttonSleep)
        buttonHome = findViewById(R.id.buttonHome)
        buttonExercise = findViewById(R.id.buttonExercise)
        kCALS = findViewById(R.id.textView2)
        water = findViewById(R.id.textView7)
        kCalBar = findViewById(R.id.kCalBar)
        waterBar = findViewById(R.id.waterBar)
        addMealSwitch = findViewById(R.id.switch1)
        buttonMealSearch = findViewById(R.id.button)
        CB6 = findViewById(R.id.checkBox6)
        CB7 = findViewById(R.id.checkBox7)
        CB8 = findViewById(R.id.floatingActionButton1)
        CB9 = findViewById(R.id.checkBox9)
        CB0 = findViewById(R.id.checkBox)
        CB2 = findViewById(R.id.checkBox2)
        CB3 = findViewById(R.id.checkBox3)
        CB4 = findViewById(R.id.checkBox4)
        CB10 = findViewById(R.id.checkBox10)
        CText = findViewById(R.id.editTextNumber)
        CText.text = null
        WText = findViewById(R.id.editTextNumber2)
        WText.text = null

        addMealSwitch.setOnClickListener{
            if(CB6.visibility == View.INVISIBLE)
            {
                CB6.visibility = View.VISIBLE
                CB7.visibility = View.VISIBLE
                CB8.visibility = View.VISIBLE
                CB9.visibility = View.VISIBLE
                CB10.visibility = View.VISIBLE
                CText.visibility = View.VISIBLE
                WText.visibility = View.VISIBLE
            }
            else
            {
                CB6.visibility = View.INVISIBLE
                CB7.visibility = View.INVISIBLE
                CB8.visibility = View.INVISIBLE
                CB9.visibility = View.INVISIBLE
                CB10.visibility = View.INVISIBLE
                CText.visibility = View.INVISIBLE
                WText.visibility = View.INVISIBLE
            }
        }

        var kCals = 0.0
        CB0.isChecked = settings.getBoolean("GEaten", false)
        CB2.isChecked = settings.getBoolean("FEaten", false)
        CB3.isChecked = settings.getBoolean("VEaten", false)
        CB4.isChecked = settings.getBoolean("PEaten", false)
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
        var t2 = settings.getInt("kCals", 0)
        var t3 = settings.getInt("water", 0)
        var str = "KCal Eaten Today $t2 / $kCals"
        var str2 = "Cups of Water Today $t3 / ~10"
        kCALS.text = str
        kCalBar.progress = ((t2/kCals)*100).toInt()
        water.text = str2
        waterBar.progress = (t3*10)

        CB8.setOnClickListener{
            if(CText.text != null && CText.text.toString() != "") {
                t2 += CText.text.toString().toInt()
            }
            if(WText.text != null && WText.text.toString() != "") {
                t3 += WText.text.toString().toInt()
            }
            CText.text = null
            WText.text = null
            str = "KCal Eaten Today $t2 / $kCals"
            str2 = "Cups of Water Today $t3 / ~10"
            kCALS.text = str
            kCalBar.progress = ((t2/kCals)*100).toInt()
            water.text = str2
            waterBar.progress = (t3*10)

            val edit = settings.edit()
            edit.putInt("kCals", t2)
            edit.putInt("water", t3)

            if(CB6.isChecked)
            {
                CB6.isChecked = false
                CB3.isChecked = true
                edit.putBoolean("VEaten", true)
            }
            if(CB7.isChecked)
            {
                CB7.isChecked = false
                CB2.isChecked = true
                edit.putBoolean("FEaten", true)
            }
            if(CB9.isChecked)
            {
                CB9.isChecked = false
                CB4.isChecked = true
                edit.putBoolean("PEaten", true)
            }
            if(CB10.isChecked)
            {
                CB10.isChecked = false
                CB0.isChecked = true
                edit.putBoolean("GEaten", true)
            }

            edit.apply()


        }



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
        buttonMealSearch.setOnClickListener{
            startActivity(Intent(this, MealSearch::class.java))
            finish()
        }

    }

}

