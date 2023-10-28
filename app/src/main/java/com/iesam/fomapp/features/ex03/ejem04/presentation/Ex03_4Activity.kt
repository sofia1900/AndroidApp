package com.iesam.fomapp.features.ex03.ejem04.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.app.extensions.setUrl
import com.iesam.fomapp.databinding.ActivityEx034Binding
import com.iesam.fomapp.features.ex03.ejem04.data.AlojamientoDataRepository
import com.iesam.fomapp.features.ex03.ejem04.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem04.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem04.domain.Alojamiento
import com.iesam.fomapp.features.ex03.ejem04.domain.GetAlojamientoUseCase
import com.iesam.viewtapasapp.app.serialization.GsonSerialization

class Ex03_4Activity : AppCompatActivity() {

    lateinit var binding: ActivityEx034Binding

    private val viewModel : Ex03_4ViewModel by lazy {
        Ex03_4ViewModel(GetAlojamientoUseCase(AlojamientoDataRepository(XmlLocalDataSource(this, GsonSerialization()), ApiRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        loadAlojamiento()
    }

    private fun bindView(){
        binding = ActivityEx034Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadAlojamiento(){
        setupObserver()
        viewModel.loadAlojamiento()
    }

    private fun setupObserver () {
        val observer = Observer<Ex03_4ViewModel.UiState>{
            it.alojamiento?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(alojamiento: Alojamiento){
        binding.apply {
            imageAlojamiento.setUrl(alojamiento.url)
            labelTitlleAlojamiento.text = alojamiento.title
            labelDescriptionAlojamiento.text = alojamiento.description
        }
    }
}