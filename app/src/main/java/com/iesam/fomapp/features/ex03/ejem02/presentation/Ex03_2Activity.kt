package com.iesam.fomapp.features.ex03.ejem02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.databinding.ActivityEx032Binding
import com.iesam.fomapp.features.ex03.ejem02.data.ConversationDataRepository
import com.iesam.fomapp.features.ex03.ejem02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem02.data.remote.ApiRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.FindAllConversationUseCase

class Ex03_2Activity : AppCompatActivity() {

    lateinit var binding: ActivityEx032Binding

    private val viewModel : Ex03_2ViewModel by lazy {
        Ex03_2ViewModel(FindAllConversationUseCase(ConversationDataRepository(XmlLocalDataSource(this), ApiRemoteDataSource())))
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

        binding.row1.labelName.text = listConver.get(0).name
        binding.row1.labelMsg.text = listConver.get(0).msg
        binding.row1.labelTime.text = listConver.get(0).time

    }

}