package com.iesam.fomapp.features.ex05

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.iesam.fomapp.databinding.ActivityEx05Binding
import com.iesam.fomapp.features.ex05.example01.Example01Activity
import com.iesam.fomapp.features.ex05.example02.Example02Activity

class Ex05Activity : AppCompatActivity() {

    lateinit var binding : ActivityEx05Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        setupView()
    }

    private fun bindView(){
        binding = ActivityEx05Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun setupView (){
        binding.butonEx01.setOnClickListener {
            startActivity(Intent(this, Example01Activity::class.java))
        }

        binding.butonEx02.setOnClickListener {
            startActivity(Intent(this, Example02Activity::class.java))
        }

    }
}