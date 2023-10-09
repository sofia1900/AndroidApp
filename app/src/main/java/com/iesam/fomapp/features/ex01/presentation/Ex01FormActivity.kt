package com.iesam.fomapp.features.ex01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import androidx.lifecycle.Observer

import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex01.data.local.xmlLocalDataSource
import com.iesam.fomapp.features.ex01.domain.SaveUserUseCase
import com.iesam.fomapp.features.ex01.domain.GetUserUseCase
import com.iesam.fomapp.features.ex01.domain.User
import com.iesam.fomapp.features.ex01.data.UserDataRepository

class Ex01FormActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex01_form)
        setupView()
    }

    val viewModels : Ex01FormViewModel by lazy {
        Ex01FormViewModel (
            SaveUserUseCase(UserDataRepository(xmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(xmlLocalDataSource(this)))
        )
    }

    private fun setupView(){
        val actionButtonSave = findViewById<Button>(R.id.action_save01)
        actionButtonSave.setOnClickListener {
            viewModels.saveUser(getNameInput(), getSurnameInput(), getAgeInput())
        }

        val actionButtonGet = findViewById<Button>(R.id.action_get01)
        actionButtonGet.setOnClickListener {
            setupObservers() //SUSCRIPCION
            viewModels.getUser() //EJECUTO EL HILO SECUNDARIO PARA RECOGER LA INFORMACION
        }

        val actionButtonClean = findViewById<Button>(R.id.action_clean01)
        actionButtonClean.setOnClickListener {
            //NUNCA actualizar la vista en el hilo SECUNDARIO
            cleanInput()
        }

    }

    private fun cleanInput (){
        findViewById<EditText>(R.id.input_name01).setText("")
        findViewById<EditText>(R.id.input_surname01).setText("")
        findViewById<EditText>(R.id.input_age01).setText("")
    }

    private fun getNameInput() : String =
        findViewById<EditText>(R.id.input_name01).text.toString()

    private fun getSurnameInput() : String =
        findViewById<EditText>(R.id.input_surname01).text.toString()


    private fun getAgeInput() : String =
        findViewById<EditText>(R.id.input_age01).text.toString()



    private fun setNameInput(name: String) {
        findViewById<EditText>(R.id.input_name01).setText(name)
    }

    private fun setSurnameInput(surname: String) {
        findViewById<EditText>(R.id.input_surname01).setText(surname)
    }

    private fun setAgeInput(age: String) {
        findViewById<EditText>(R.id.input_age01).setText(age)
    }

    private fun setupObservers(){
        val observer = Observer<Ex01FormViewModel.UiState>{
            it.user?.apply {
                bindData(this)
            }
        }
        viewModels.uiState.observe(this, observer)
    }

    private fun bindData (user : User){
        setNameInput(user.username)
        setSurnameInput(user.surname)
        setAgeInput(user.age)
    }
}