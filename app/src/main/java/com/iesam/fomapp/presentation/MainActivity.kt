package com.iesam.fomapp.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.data.UserDataRepository
import com.iesam.fomapp.data.local.XmlLocalDataSource
import com.iesam.fomapp.domain.User
import com.iesam.fomapp.domain.useCases.DeleteUserUseCase
import com.iesam.fomapp.domain.useCases.GetUserUseCase
import com.iesam.fomapp.domain.useCases.SaveUserUseCase


class MainActivity : AppCompatActivity() {

    private val viewModel : MainViewModel by lazy {
        MainViewModel (SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            DeleteUserUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setupView()
    }

    private fun setupView (){
        val actionButtonSave = findViewById<Button>(R.id.action_save)
        actionButtonSave.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput()) //GUARDO LOS DATOS
            getUser()
        }

        val actionButtonClean = findViewById<Button>(R.id.action_clean)
        actionButtonClean.setOnClickListener {
            cleanInput()
        }

        val actionButtonDelete = findViewById<Button>(R.id.action_delete1)
        actionButtonDelete.setOnClickListener {
            //ELIMINAR DE LA PANTALLA
            cleanText()
            invisibleElements()
            //ELIMINAR DE LOCAL
            viewModel.deleteUser()
        }

    }

    private fun getUser (){
        setupObservers() //SUSCRIPCION
        viewModel.getUser() //EJECUTO EL HILO SECUNDARIO PARA RECOGER LA INFORMACION
    }

    private fun setupObservers (){
        val observer = Observer<MainViewModel.UiState>{
            it.user?.apply {
                visibleElements()
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(user : User){
        setNameInput(user.name)
        setSurnameInput(user.surname)
    }

    private fun visibleElements (){
        findViewById<LinearLayout>(R.id.linear1).visibility = View.VISIBLE
    }

    private fun setNameInput (name : String){
        findViewById<TextView>(R.id.text_name1).setText(name)
    }

    private fun setSurnameInput (surname : String){
        findViewById<TextView>(R.id.text_surname1).setText(surname)
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()


    private fun cleanInput () {
        findViewById<EditText>(R.id.input_name).setText("")
        findViewById<EditText>(R.id.input_surname).setText("")
    }

    private fun cleanText (){
        findViewById<TextView>(R.id.text_name1).setText("")
        findViewById<TextView>(R.id.text_surname1).setText("")
    }

    private fun invisibleElements(){
        findViewById<LinearLayout>(R.id.linear1).visibility = View.GONE
    }
}