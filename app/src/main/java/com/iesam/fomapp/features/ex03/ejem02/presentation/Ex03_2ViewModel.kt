package com.iesam.fomapp.features.ex03.ejem02.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.features.ex03.ejem02.domain.Conversation
import com.iesam.fomapp.features.ex03.ejem02.domain.FindAllConversationUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class Ex03_2ViewModel (private val findAllConversationUseCase: FindAllConversationUseCase): ViewModel() {

    private val _uiState = MutableLiveData<Ex03_2ViewModel.UiState>()
    val uiState: LiveData<Ex03_2ViewModel.UiState> = _uiState

    fun findAllConversation () {
        viewModelScope.launch(Dispatchers.IO){
            findAllConversationUseCase().fold(
                {responseError(it)},
                {responseSucess(it)}
            )
        }
    }

    private fun responseError(errorApp: ErrorApp) {
    }

    private fun responseSucess (listConversation: List<Conversation>){
        _uiState.postValue(Ex03_2ViewModel.UiState(listConversations = listConversation))
    }

    data class UiState(
        val errorApp: ErrorApp? = null,
        val isLoading: Boolean = false,
        val listConversations : List<Conversation>? = null
    )


}