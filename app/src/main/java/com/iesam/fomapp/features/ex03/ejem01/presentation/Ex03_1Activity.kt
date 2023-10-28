package com.iesam.fomapp.features.ex03.ejem01.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.app.extensions.setUrl
import com.iesam.fomapp.databinding.ActivityEx031Binding
import com.iesam.fomapp.features.ex03.ejem01.data.BurgerDataRepository
import com.iesam.fomapp.features.ex03.ejem01.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem01.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem01.domain.Burger
import com.iesam.fomapp.features.ex03.ejem01.domain.GetBurgerUseCase
import com.iesam.viewtapasapp.app.serialization.GsonSerialization

class Ex03_1Activity : AppCompatActivity() {

    lateinit var binding : ActivityEx031Binding

    private val viewModel : Ex03_1ViewModel by lazy {
        Ex03_1ViewModel(GetBurgerUseCase(BurgerDataRepository(XmlLocalDataSource(this, GsonSerialization()), ApiRemoteDataSource())))
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
        binding.apply {
            labelOfert.text = burger.discount
            labelTittle.text = burger.title
            labelLikes.text = burger.rate
            labelTime.text = burger.time
            imageBurger.setUrl(burger.url_image)
        }

    }
}