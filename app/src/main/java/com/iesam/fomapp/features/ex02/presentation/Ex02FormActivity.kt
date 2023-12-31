package com.iesam.fomapp.features.ex02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex02.data.UserDataRepository
import com.iesam.fomapp.features.ex02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex02.domain.User
import com.iesam.fomapp.features.ex02.domain.useCases.DeleteUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.FindAllUsersUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.GetUserUseCase
import com.iesam.fomapp.features.ex02.domain.useCases.SaveUserUseCase


class Ex02FormActivity : AppCompatActivity() {

    private val viewModel : Ex02ViewModel by lazy {
        Ex02ViewModel (SaveUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            GetUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            DeleteUserUseCase(UserDataRepository(XmlLocalDataSource(this))),
            FindAllUsersUseCase(UserDataRepository(XmlLocalDataSource(this)))
        )
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex02_form)
        setupView()
        loadUsers()
    }

    private fun setupView (){
        //SAVE AND UPDATE USERS
        val actionButtonSave = findViewById<Button>(R.id.action_save)
        actionButtonSave.setOnClickListener {
            viewModel.saveUser(getNameInput(), getSurnameInput()) //GUARDO LOS DATOS
            loadUsers()
        }

        //CLEAN VIEW
        val actionButtonClean = findViewById<Button>(R.id.action_clean)
        actionButtonClean.setOnClickListener {
            cleanInput()
        }

        //DELETE
        val row1 = findViewById<ViewGroup>(R.id.row_1)
        val actionButtonDelete1 = row1.findViewById<Button>(R.id.action_delete)
        actionButtonDelete1.setOnClickListener {
            cleanView(row1)
            deleteUser(row1)
        }
        val row2 = findViewById<ViewGroup>(R.id.row_2)
        val actionButtonDelete2 = row2.findViewById<Button>(R.id.action_delete)
        actionButtonDelete2.setOnClickListener {
            cleanView(row2)
            deleteUser(row2)
        }
        val row3 = findViewById<ViewGroup>(R.id.row_3)
        val actionButtonDelete3 = row3.findViewById<Button>(R.id.action_delete)
        actionButtonDelete3.setOnClickListener {
            cleanView(row3)
            deleteUser(row3)
        }
        val row4 = findViewById<ViewGroup>(R.id.row_4)
        val actionButtonDelete4 = row4.findViewById<Button>(R.id.action_delete)
        actionButtonDelete4.setOnClickListener {
            cleanView(row4)
            deleteUser(row4)
        }
        val row5 = findViewById<ViewGroup>(R.id.row_5)
        val actionButtonDelete5 = row5.findViewById<Button>(R.id.action_delete)
        actionButtonDelete5.setOnClickListener {
            cleanView(row5)
            deleteUser(row5)
        }

    }

    private fun loadUsers (){
        setupObservers()
        viewModel.getUsers()
    }

    private fun setupObservers (){
        val observer = Observer<Ex02ViewModel.UiState>{
            it.listUsers?.apply {//Hacer cuando sea avisado
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun cleanInput () {
        findViewById<EditText>(R.id.input_name).setText("")
        findViewById<EditText>(R.id.input_surname).setText("")
    }

    private fun cleanView(row : ViewGroup){
        cleanText(row)
        invisibleButtomDelete(row)
    }
    private fun cleanText (row : ViewGroup){
        row.findViewById<TextView>(R.id.label_name).setText("")
        row.findViewById<TextView>(R.id.label_surname).setText("")
    }
    private fun invisibleButtomDelete (row : ViewGroup){
        row.findViewById<Button>(R.id.action_delete).visibility = View.INVISIBLE
    }

    private fun deleteUser (row : ViewGroup){
        val idUser = row.findViewById<TextView>(R.id.label_id).text.toString().toInt()
        viewModel.deleteUser(idUser)
    }

    private fun bindData (users : List<User>){
        cleanTotalView()

        if (users.size == 1){
            updatRow1(users)
        }else if (users.size == 2){
            updatRow1(users)
            updateRow2(users)
        }else if (users.size == 3) {
            updatRow1(users)
            updateRow2(users)
            updateRow3(users)
        }else if (users.size == 4) {
            updatRow1(users)
            updateRow2(users)
            updateRow3(users)
            updateRow4(users)
        }else if (users.size == 5){
            updatRow1(users)
            updateRow2(users)
            updateRow3(users)
            updateRow4(users)
            updateRow5(users)
        }
    }

    private fun updatRow1 (users : List<User>){
        val row = findViewById<ViewGroup>(R.id.row_1)
        updateRow(row, users, 0)
    }
    private fun updateRow2 (users : List<User>){
        val row = findViewById<ViewGroup>(R.id.row_2)
        updateRow(row, users, 1)
    }
    private fun updateRow3 (users : List<User>){
        val row = findViewById<ViewGroup>(R.id.row_3)
        updateRow(row, users, 2)
    }
    private fun updateRow4 (users : List<User>){
        val row = findViewById<ViewGroup>(R.id.row_4)
        updateRow(row, users, 3)
    }
    private fun updateRow5 (users : List<User>){
        val row = findViewById<ViewGroup>(R.id.row_5)
        updateRow(row, users, 4)
    }

    private fun updateRow (row : ViewGroup, users : List<User>, pos : Int){
        row.findViewById<TextView>(R.id.label_name).setText(users.get(pos).name)
        row.findViewById<TextView>(R.id.label_surname).setText(users.get(pos).surname)
        row.findViewById<TextView>(R.id.label_id).setText(users.get(pos).id.toString())
        row.findViewById<Button>(R.id.action_delete).visibility = View.VISIBLE
    }

    private fun getNameInput(): String =
        findViewById<EditText>(R.id.input_name).text.toString()

    private fun getSurnameInput(): String =
        findViewById<EditText>(R.id.input_surname).text.toString()


    private fun cleanTotalView(){
        val row1 = findViewById<ViewGroup>(R.id.row_1)
        val row2 = findViewById<ViewGroup>(R.id.row_2)
        val row3 = findViewById<ViewGroup>(R.id.row_3)
        val row4 = findViewById<ViewGroup>(R.id.row_4)
        val row5 = findViewById<ViewGroup>(R.id.row_5)

        val rows = mutableListOf<ViewGroup>(row1, row2, row3, row4, row5)

        for (row in rows){
            cleanView(row)
        }

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