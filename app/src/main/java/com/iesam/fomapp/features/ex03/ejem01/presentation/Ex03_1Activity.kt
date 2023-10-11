package com.iesam.fomapp.features.ex03.ejem01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex03.ejem01.data.BurgerDataRepository
import com.iesam.fomapp.features.ex03.ejem01.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem01.data.remote.ApiMockRemoteLocalDataSource
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger
import com.iesam.fomapp.features.ex03.ejem01.domain.GetBurgerUseCase

class Ex03_1Activity : AppCompatActivity() {

    private val viewModel : Ex03_1ViewModel by lazy {
        Ex03_1ViewModel(GetBurgerUseCase(BurgerDataRepository(XmlLocalDataSource(this), ApiMockRemoteLocalDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex03_1)
        setupView()
    }

    private fun setupView (){
        loadBurger()
    }

    private fun loadBurger(){
        setupObserver()
        viewModel.getUser()
    }

    private fun setupObserver (){
        val observer = Observer<Ex03_1ViewModel.UiState>{
            it.burger?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData (burger : Burger){
        findViewById<TextView>(R.id.label_ofert).text = burger.ofert
        findViewById<TextView>(R.id.label_tittle).text = burger.tittle
        findViewById<TextView>(R.id.label_likes).text = burger.likes
        findViewById<TextView>(R.id.label_time).text = burger.time
    }
}