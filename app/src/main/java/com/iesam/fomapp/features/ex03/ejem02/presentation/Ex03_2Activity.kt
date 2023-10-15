package com.iesam.fomapp.features.ex03.ejem02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.databinding.ActivityEx032Binding
import com.iesam.fomapp.features.ex03.ejem02.data.ConversationDataRepository
import com.iesam.fomapp.features.ex03.ejem02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem02.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.FindAllConversationUseCase

class Ex03_2Activity : AppCompatActivity() {

    lateinit var binding: ActivityEx032Binding

    private val viewModel : Ex03_2ViewModel by lazy {
        Ex03_2ViewModel(FindAllConversationUseCase(ConversationDataRepository(XmlLocalDataSource(this), ApiMockRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindView()
        loadConversations()
    }
    private fun bindView (){
        binding = ActivityEx032Binding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
    }

    private fun loadConversations(){
        setupObserver()
        viewModel.findAllConversation()
    }

    private fun setupObserver(){
        val observer = Observer<Ex03_2ViewModel.UiState>{
            it.listConversations?.apply {
                bindData(this)
            }
        }
        viewModel.uiState.observe(this, observer)
    }

    private fun bindData (listConver : List<Conversation>){

        binding.labelNameC.text = listConver.get(0).name
        binding.labelNamePerson.text = listConver.get(0).text
        binding.labelMsg1.text = listConver.get(0).msg
        binding.labelTimeP1.text = listConver.get(0).time

        binding.labelNameC1.text = listConver.get(1).name
        binding.labelNamePerson1.text = listConver.get(1).text
        binding.labelTextPerson1.text = listConver.get(1).msg
        binding.labelTimeP2.text = listConver.get(1).time

    }

}