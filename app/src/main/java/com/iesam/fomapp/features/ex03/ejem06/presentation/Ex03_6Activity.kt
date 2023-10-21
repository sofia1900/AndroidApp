package com.iesam.fomapp.features.ex03.ejem06.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.app.extensions.setUrl
import com.iesam.fomapp.databinding.ActivityEx036Binding
import com.iesam.fomapp.features.ex03.ejem06.data.DogDataRepository
import com.iesam.fomapp.features.ex03.ejem06.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem06.domain.GetDogUseCase
import com.iesam.fomapp.features.ex03.ejem06.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem06.domain.Dog
import com.iesam.viewtapasapp.app.serialization.GsonSerialization

class Ex03_6Activity : AppCompatActivity() {

    lateinit var binding: ActivityEx036Binding

    private val viewModel : Ex03_6ViewModel by lazy {
        Ex03_6ViewModel(GetDogUseCase(DogDataRepository(XmlLocalDataSource(this, GsonSerialization() ), ApiRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        loadDog()
    }

    private fun bindView (){
        binding = ActivityEx036Binding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    private fun loadDog(){
        setupObserver()
        viewModel.loadBurger()
    }

    private fun setupObserver () {
        val observer = Observer<Ex03_6ViewModel.UiState>{
            it.dog?.apply{
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData(dog : Dog){
        binding.apply {
            imageRami.setUrl(dog.url)
            labelNameDog.text = dog.name
            labelDescriptionDop.text = dog.description
            labelSexDog.text = dog.sex
            labelDateDog.text = dog.date
        }
    }
}