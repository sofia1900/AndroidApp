package com.iesam.fomapp.features.ex03

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex03.ejem01.presentation.Ex03_1Activity
import com.iesam.fomapp.features.ex03.ejem02.presentation.Ex03_2Activity
import com.iesam.fomapp.features.ex03.ejem03.presentation.Ex03_3Activity
import com.iesam.fomapp.features.ex04.Ex03_4Activity

class Ex03Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex03)
        setupView()
    }

    private fun setupView (){
        findViewById<Button>(R.id.action_3_1).setOnClickListener {
            startActivity(Intent(this, Ex03_1Activity::class.java))
        }

        findViewById<Button>(R.id.action_3_2).setOnClickListener {
            startActivity(Intent(this, Ex03_2Activity::class.java))
        }

        findViewById<Button>(R.id.action_3_3).setOnClickListener {
            startActivity(Intent(this, Ex03_3Activity::class.java))
        }

        findViewById<Button>(R.id.action_3_4).setOnClickListener {
            startActivity(Intent(this, Ex03_4Activity::class.java))
        }
    }
}