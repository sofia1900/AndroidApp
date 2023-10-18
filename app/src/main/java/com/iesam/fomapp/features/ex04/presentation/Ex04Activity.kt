package com.iesam.fomapp.features.ex04.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.extensions.hide
import com.iesam.fomapp.app.extensions.setUrl
import com.iesam.fomapp.app.extensions.visible
import com.iesam.fomapp.databinding.ActivityEx04Binding
import com.iesam.fomapp.features.ex04.data.BurgerDataRepository
import com.iesam.fomapp.features.ex04.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex04.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex04.domain.Burger
import com.iesam.fomapp.features.ex04.domain.GetBurgerUseCase


class Ex04Activity : AppCompatActivity() {

    private lateinit var binding: ActivityEx04Binding

    private val viewModel : Ex04ViewModel by lazy {
        Ex04ViewModel(GetBurgerUseCase(BurgerDataRepository(XmlLocalDataSource(this), ApiMockRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupBinding()
        loadBurger()
    }

    private fun setupBinding(){
        binding = ActivityEx04Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadBurger(){
        setupObserver()
        viewModel.loadBurger()
    }

    private fun setupObserver (){
        val observer = Observer<Ex04ViewModel.UiState>{

            if(it.isLoading){
                //Muestro el loading
                showLoading()
            }else{
                //Oculto el loading
                hideLoading()
            }

            it.errorApp?.let {
                showError(it)
            }
            it.burger?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun showLoading (){
        binding.skeletonLayout.showSkeleton()
    }

    private fun hideLoading(){
        binding.skeletonLayout.showOriginal()
    }

    private fun showError(error : ErrorApp){
        binding.viewError.layoutError.visible()
        binding.layoutView.hide()
        when(error){
            ErrorApp.UnknowError -> binding.viewError.labelMesaggeError.text =
                getString(R.string.label_unknow_error)
        }
    }

    private fun bindData (burger : Burger){

        binding.apply {
            imageBurger.setUrl("https://goo.gl/gEgYUd")
            labelOfert.text =  burger.ofert
            labelTittle.text = burger.tittle
            labelLikes.text =  burger.likes
            labelTime.text = burger.time
        }
    }


}