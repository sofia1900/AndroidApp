package com.iesam.fomapp.features.ex03.presentation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iesam.fomapp.R

class Ex03Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex03)
    }

    private fun setupView (){
        findViewById<Button>(R.id.action_3_1).setOnClickListener {
            startActivity(Intent(this, Ex03_1Activity::class.java))
        }
    }
}