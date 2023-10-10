package com.iesam.fomapp.features.ex02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex02.data.UserDataRepository
import com.iesam.fomapp.features.ex02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex02.domain.User
import com.iesam.fomapp.features.ex02.domain.useCases.DeleteUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.GetUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.SaveUserUseCase


class Ex02FormActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex02_form)
        setupView()
    }


    private val viewModel : Ex02ViewModel by lazy {
        Ex02ViewModel (SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            DeleteUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    private fun setupView (){
        val actionButtonSave = findViewById<Button>(R.id.action_save)
        actionButtonSave.setOnClickListener {
            val idUser : Int = viewModel.saveUser(getNameInput(), getSurnameInput()) //GUARDO LOS DATOS -- recojo el id creado
            Log.d("@dev", idUser.toString())
            getUser(idUser) //Recuperar datos y pintar
        }

        val actionButtonClean = findViewById<Button>(R.id.action_clean)
        actionButtonClean.setOnClickListener {
            cleanInput()
        }

        val actionButtonDelete = findViewById<Button>(R.id.action_delete1)
        actionButtonDelete.setOnClickListener {
            //ELIMINAR DE LA PANTALLA
            cleanText()
            invisibleButtomDelete1()
            //ELIMINAR DE LOCAL
            //viewModel.deleteUser()
        }

    }

    private fun getUser (idUser : Int){
        setupObservers() //SUSCRIPCION
        viewModel.getUser(idUser) //EJECUTO EL HILO SECUNDARIO PARA RECOGER LA INFORMACION
    }

    private fun setupObservers (){
        val observer = Observer<Ex02ViewModel.UiState>{
            it.user?.apply {//Hacer cuando sea avisado
                visibleButtomDelete1()
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(user : User){
        setNameInput(user.name)
        setSurnameInput(user.surname)
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()



    private fun setNameInput (name : String){
        findViewById<TextView>(R.id.text_name1).setText(name)
    }

    private fun setSurnameInput (surname : String){
        findViewById<TextView>(R.id.text_surname1).setText(surname)
    }

    private fun cleanInput () {
        findViewById<EditText>(R.id.input_name).setText("")
        findViewById<EditText>(R.id.input_surname).setText("")
    }

    private fun cleanText (){
        findViewById<TextView>(R.id.text_name1).setText("")
        findViewById<TextView>(R.id.text_surname1).setText("")
    }

    private fun visibleButtomDelete1 (){
        findViewById<LinearLayout>(R.id.action_delete1).visibility = View.VISIBLE
    }
    private fun invisibleButtomDelete1 (){
        findViewById<LinearLayout>(R.id.action_delete1).visibility = View.INVISIBLE
    }
}