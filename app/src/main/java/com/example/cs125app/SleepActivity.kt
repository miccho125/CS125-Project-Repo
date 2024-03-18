package com.example.cs125app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import android.widget.Button
import android.widget.EditText
import android.widget.NumberPicker
import android.widget.RadioGroup

import android.widget.TimePicker
import android.widget.TextView
import android.widget.RatingBar

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager



public class SleepActivity : ComponentActivity(), SensorEventListener {

    lateinit var buttonDiet: Button
    lateinit var buttonHome: Button
    lateinit var buttonExercise: Button

    lateinit var enterSleepButton: Button
    lateinit var sleepStartPicker: TimePicker
    lateinit var sleepEndPicker: TimePicker
    lateinit var userHoursTextView: TextView
    private var sleepRatingValue: Float = 0.0f
    lateinit var sleepRecommendation: TextView

    private lateinit var sensorManager: SensorManager
    private var temperatureSensor: Sensor? = null
    private var currentTemperature: Float = Float.NaN

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sleep)

        buttonDiet = findViewById(R.id.buttonDiet)
        buttonHome = findViewById(R.id.buttonHome)
        buttonExercise = findViewById(R.id.buttonExercise)

        enterSleepButton = findViewById(R.id.enterSleep)
        sleepStartPicker = findViewById(R.id.sleepStart)
        sleepEndPicker = findViewById(R.id.sleepEnd)
        userHoursTextView = findViewById(R.id.userHours)
        sleepRecommendation = findViewById(R.id.sleepRecommendation)

        val sleepRatingValue = findViewById<RatingBar>(R.id.sleepRating)

        sensorManager = getSystemService(Context.SENSOR_SERVICE) as SensorManager
        temperatureSensor = sensorManager.getDefaultSensor(Sensor.TYPE_AMBIENT_TEMPERATURE)
        if (temperatureSensor == null) {
            println("Temperature sensor not available")
        }

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

        enterSleepButton.setOnClickListener {
            val startHour = sleepStartPicker.hour
            val startMinute = sleepStartPicker.minute
            val endHour = sleepEndPicker.hour
            val endMinute = sleepEndPicker.minute

            val startTimeMinutes = startHour * 60 + startMinute
            //val endTimeMinutes = endHour * 60 + endMinute
            val endTimeMinutes = if (endHour < startHour || (endHour == startHour && endMinute < startMinute)) {
                endHour * 60 + endMinute + 24 * 60
            } else {
                endHour * 60 + endMinute
            }
            var durationMinutes = endTimeMinutes - startTimeMinutes
            val durationHours = durationMinutes / 60
            durationMinutes %= 60
//            userHoursTextView.text = "$durationHours hours $durationMinutes minutes"
            val durationString = buildString {
                if (durationHours > 0) {
                    append("$durationHours hours \n")
                }
                if (durationMinutes > 0) {
                    append("$durationMinutes minutes")
                }
            }
            userHoursTextView.text = durationString

            temperatureSensor?.let {
                sensorManager.registerListener(this, it, SensorManager.SENSOR_DELAY_NORMAL)
                println("Temperature: $currentTemperature")
            }
            val sleepRatingValue = sleepRatingValue.rating
            println("Sleep Rating: $sleepRatingValue")

            if(durationHours in 7..9 && sleepRatingValue > 3){
                sleepRecommendation.text = "You seem to be getting good quality sleep for close to the recommended amount of hours!"
            }
            else if(durationHours < 7 && durationString != ""){
                val hoursToGoEarlier = 7 - durationHours
                val minutesToGoEarlier = 60 - durationMinutes
                val message = buildString {
                    append("You are getting less sleep than recommended. ")
                    if (hoursToGoEarlier > 0) {
                        append("Try going to sleep $hoursToGoEarlier hour")
                        if (hoursToGoEarlier > 1) append("s")
                        if (minutesToGoEarlier > 0) append(" and ")
                    }
                    if (minutesToGoEarlier > 0) {
                        append("$minutesToGoEarlier minute")
                        if (minutesToGoEarlier > 1) append("s")
                        append(" earlier")
                    }
                    append(".")
                }
                sleepRecommendation.text = message
            }
            else if(durationHours >= 10 && durationString != ""){
                sleepRecommendation.text = "Continuous excess sleeping can be caused by stress. Try meditating or journaling, and contact your doctor if it persists."
            }
            else if(sleepRatingValue < 4 && durationString != ""){
                if(currentTemperature > 65){
                    sleepRecommendation.text = "Temperature may be reducing your quality of sleep. Try lowering the temperature below 65 degrees. "
                }
                if(currentTemperature < 60){
                    sleepRecommendation.text = "Temperature may be reducing your quality of sleep. Try increasing the temperature above 60 degrees. "
                }
            }

        }

    }
    override fun onSensorChanged(event: SensorEvent) {
        if (event.sensor.type == Sensor.TYPE_AMBIENT_TEMPERATURE) {
            currentTemperature = event.values[0]
            //println("Temperature: $currentTemperature")
        }
    }

    override fun onAccuracyChanged(sensor: Sensor?, accuracy: Int) {

    }
    override fun onPause() {
        super.onPause()
        sensorManager.unregisterListener(this)
    }

}