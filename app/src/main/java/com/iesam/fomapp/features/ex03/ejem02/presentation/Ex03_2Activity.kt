package com.iesam.fomapp.features.ex03.ejem02.presentation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import com.iesam.fomapp.R
import com.iesam.fomapp.features.ex03.ejem02.data.ConversationDataRepository
import com.iesam.fomapp.features.ex03.ejem02.data.local.XmlLocalDataSource
import com.iesam.fomapp.features.ex03.ejem02.data.remote.ApiMockRemoteDataSource
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.FindAllConversationUseCase

class Ex03_2Activity : AppCompatActivity() {

    private val viewModel : Ex03_2ViewModel by lazy {
        Ex03_2ViewModel(FindAllConversationUseCase(ConversationDataRepository(XmlLocalDataSource(this), ApiMockRemoteDataSource())))
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ex03_2)
        loadConversations()
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
        //VISTA
    }

}