package com.iesam.fomapp.features.ex03.ejem03.presentation


import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.databinding.ActivityEx033Binding
import com.iesam.fomapp.features.ex03.ejem03.data.FilmDataRepository
import com.iesam.fomapp.features.ex03.ejem03.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem03.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem03.domain.Film
import com.iesam.fomapp.features.ex03.ejem03.domain.FindAllFilmsUseCase

class Ex03_3Activity : AppCompatActivity() {

    lateinit var binding: ActivityEx033Binding

    private val viewModel : Ex03_3ViewModel by lazy {
        Ex03_3ViewModel(FindAllFilmsUseCase(FilmDataRepository(XmlLocalDataSource(this), ApiMockRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        loadFilms()
    }
    private fun bindView(){
        binding = ActivityEx033Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun loadFilms (){
        setupObserver()
        viewModel.findAllFilms()
    }

    private fun setupObserver(){
        val observer = Observer<Ex03_3ViewModel.UiState>{
            it.listFilms?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData (listFilms : List<Film>){
        binding.labelTittleP1.text = listFilms.get(0).tittle
        binding.labelPuntuacion1.text = listFilms.get(0).range
        binding.labelSpace1.text = listFilms.get(0).space
        binding.labelTimePeli1.text = listFilms.get(0).time

        binding.labelTittleP2.text = listFilms.get(1).tittle
        binding.labelPuntuacion2.text = listFilms.get(1).range
        binding.labelSpace2.text = listFilms.get(1).space
        binding.labelTimePeli2.text = listFilms.get(1).time
    }

}