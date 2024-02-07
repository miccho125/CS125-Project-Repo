package com.example.cs125app

import android.os.Bundle
import android.content.SharedPreferences
import androidx.activity.ComponentActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.cs125app.ui.theme.CS125AppTheme

public class MainActivity : ComponentActivity() {

    lateinit var username: EditText;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        username = findViewById(R.id.EditUserName)

        val settings = getSharedPreferences("UserInfo", 0)
        val n = settings.getString("username","")
        username.setText(n)
    }

    override fun onStop()
    {
        super.onStop()
        val settings = getSharedPreferences("UserInfo", 0)
        val edit = settings.edit()

        edit.putString("username",username.text.toString())
        edit.apply()

    }

}

