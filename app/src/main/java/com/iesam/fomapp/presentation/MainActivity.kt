package com.iesam.fomapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import com.iesam.fomapp.R
import com.iesam.fomapp.data.UserDataRepository
import com.iesam.fomapp.data.local.XmlLocalDataSource
import com.iesam.fomapp.domain.useCases.SaveUserUseCase


class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by lazy {
        MainViewModel (SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView (){
        val actionButtonSave = findViewById<Button>(R.id.action_save)
        actionButtonSave.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput())
        }
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()
}