package com.iesam.fomapp.features.ex03.ejem01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.databinding.ActivityEx031Binding
import com.iesam.fomapp.features.ex03.ejem01.data.BurgerDataRepository
import com.iesam.fomapp.features.ex03.ejem01.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem01.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem01.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger
import com.iesam.fomapp.features.ex03.ejem01.domain.GetBurgerUseCase

class Ex03_1Activity : AppCompatActivity() {

    lateinit var binding : ActivityEx031Binding

    private val viewModel : Ex03_1ViewModel by lazy {
        Ex03_1ViewModel(GetBurgerUseCase(BurgerDataRepository(XmlLocalDataSource(this), ApiRemoteDataSource())))
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        loadBurger()
    }

    private fun bindView(){
        binding = ActivityEx031Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun loadBurger(){
        setupObserver()
        viewModel.loadBurger()
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
        findViewById<TextView>(R.id.label_ofert).text = burger.discount
        findViewById<TextView>(R.id.label_tittle).text = burger.title
        findViewById<TextView>(R.id.label_likes).text = burger.rate
        findViewById<TextView>(R.id.label_time).text = burger.time
    }
}