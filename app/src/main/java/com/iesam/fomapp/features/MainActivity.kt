package com.iesam.fomapp.features

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex02.presentation.Ex02FormActivity
import java.text.Normalizer.Form

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun setupView (){
        findViewById<Button>(R.id.action_02Form).setOnClickListener {
            startActivity(Intent(this, Ex02FormActivity::class.java))
        }
    }
}