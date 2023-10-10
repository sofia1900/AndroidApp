package com.iesam.fomapp.features.ex02.presentation

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import androidx.lifecycle.Observer
import com.google.gson.Gson
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex02.data.UserDataRepository
import com.iesam.fomapp.features.ex02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex02.domain.User
import com.iesam.fomapp.features.ex02.domain.useCases.DeleteUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.FindAllUsersUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.GetUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.SaveUserUseCase


class Ex02FormActivity : AppCompatActivity() {

    val gson = Gson()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex02_form)
        setupView()
    }


    private val viewModel : Ex02ViewModel by lazy {
        Ex02ViewModel (SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            DeleteUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            FindAllUsersUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }

    private fun setupView (){

        //SAVE AND PRINT
        val actionButtonSave = findViewById<Button>(R.id.action_save)
        actionButtonSave.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput()) //GUARDO LOS DATOS
            //getUser(idUser) //Recuperar datos y pintar UN usuario
            getUsers()
        }

        //CLEAN
        val actionButtonClean = findViewById<Button>(R.id.action_clean)
        actionButtonClean.setOnClickListener {
            cleanInput()
        }

        //BUTTOM
        val row1 = findViewById<ViewGroup>(R.id.row_1)
        val actionButtonDelete1 = row1.findViewById<Button>(R.id.action_delete)
        actionButtonDelete1.setOnClickListener {
            //ELIMINAR DE LA PANTALLA
            cleanText(row1)
            invisibleButtomDelete(row1)
            //ELIMINAR DE LOCAL
            val idUser = row1.findViewById<TextView>(R.id.label_id).text.toString().toInt()
            viewModel.deleteUser(idUser)
        }

        val row2 = findViewById<ViewGroup>(R.id.row_2)
        val actionButtonDelete2 = row2.findViewById<Button>(R.id.action_delete)
        actionButtonDelete2.setOnClickListener {
            //ELIMINAR DE LA PANTALLA
            cleanText(row2)
            invisibleButtomDelete(row2)
            //ELIMINAR DE LOCAL
            val idUser = row2.findViewById<TextView>(R.id.label_id).text.toString().toInt()
            viewModel.deleteUser(idUser)
        }

    }

    private fun getUsers(){
        setupObservers() //Suscripcion
        viewModel.getUsers()
    }

    private fun setupObservers (){
        val observer = Observer<Ex02ViewModel.UiState>{
            it.mapUsers?.apply {//Hacer cuando sea avisado
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData (mapUsers : Map<String, String>){
        val users = mapUsers.values.map{
            gson.fromJson(it, User::class.java)
        }

        if (users.size == 1){
            val row = findViewById<ViewGroup>(R.id.row_1)
            row.findViewById<TextView>(R.id.label_name).setText(users.get(0).name)
            row.findViewById<TextView>(R.id.label_surname).setText(users.get(0).surname)
            row.findViewById<Button>(R.id.action_delete).visibility = View.VISIBLE
        }else if (users.size == 2){
            val row1 = findViewById<ViewGroup>(R.id.row_1)
            row1.findViewById<TextView>(R.id.label_name).setText(users.get(0).name)
            row1.findViewById<TextView>(R.id.label_surname).setText(users.get(0).surname)
            row1.findViewById<Button>(R.id.action_delete).visibility = View.VISIBLE

            val row2 = findViewById<ViewGroup>(R.id.row_2)
            row2.findViewById<TextView>(R.id.label_name).setText(users.get(0).name)
            row2.findViewById<TextView>(R.id.label_surname).setText(users.get(0).surname)
            row2.findViewById<Button>(R.id.action_delete).visibility = View.VISIBLE
        }
    }


    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()


    private fun cleanInput () {
        findViewById<EditText>(R.id.input_name).setText("")
        findViewById<EditText>(R.id.input_surname).setText("")
    }


    private fun cleanText (row : ViewGroup ){
        row.findViewById<TextView>(R.id.label_name).setText("")
        row.findViewById<TextView>(R.id.label_surname).setText("")
    }
    private fun invisibleButtomDelete (row : ViewGroup){
        row.findViewById<Button>(R.id.action_delete).visibility = View.INVISIBLE
    }

    /*
    private fun getUser (idUser : Int){
        setupObservers() //SUSCRIPCION
        viewModel.getUser(idUser) //EJECUTO EL HILO SECUNDARIO PARA RECOGER LA INFORMACION
    }

    private fun setupObservers (){
        val observer = Observer<Ex02ViewModel.UiState>{
            it.user?.apply {//Hacer cuando sea avisado
                //visibleButtomDelete1()
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(user : User){
        setNameInput(user.name)
        setSurnameInput(user.surname)
        setIdInput(user.id)
    }

    private fun visibleButtomDelete1 (){
        findViewById<Button>(R.id.action_delete1).visibility = View.VISIBLE
    }
    private fun invisibleButtomDelete1 (){
        findViewById<Button>(R.id.action_delete1).visibility = View.INVISIBLE
    }

    private fun setNameInput (name : String){
        findViewById<TextView>(R.id.label_name1).setText(name)
    }

    private fun setSurnameInput (surname : String){
        findViewById<TextView>(R.id.label_surname1).setText(surname)
    }
    private fun setIdInput (id : Int){
        findViewById<TextView>(R.id.label_id1).setText(id.toString())
    }
     */
}