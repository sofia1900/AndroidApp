package com.iesam.fomapp.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex01.presentation.Ex01FormActivity
import com.iesam.fomapp.features.ex02.presentation.Ex02FormActivity
import com.iesam.fomapp.features.ex03.Ex03Activity
import com.iesam.fomapp.features.ex04.presentation.Ex04Activity
import com.iesam.fomapp.features.ex05.Ex05Activity

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView (){

        findViewById<Button>(R.id.action_01Form).setOnClickListener {
            startActivity(Intent(this, Ex01FormActivity::class.java))
        }

        findViewById<Button>(R.id.action_02Form).setOnClickListener {
            startActivity(Intent(this, Ex02FormActivity::class.java))
        }

        findViewById<Button>(R.id.action_03Form).setOnClickListener {
            startActivity(Intent(this, Ex03Activity::class.java))
        }

        findViewById<Button>(R.id.action_04Form).setOnClickListener {
            startActivity(Intent(this, Ex04Activity::class.java))
        }

        findViewById<Button>(R.id.action_05Styles).setOnClickListener {
            startActivity(Intent(this, Ex05Activity::class.java))
        }
    }
}