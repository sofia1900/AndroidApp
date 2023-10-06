package com.iesam.fomapp.domain.useCases

import com.iesam.fomapp.data.UserDataRepository

class SaveUserUseCase (private val userDataRepository: UserDataRepository){

     operator fun invoke (name : String, surname : String){
        userDataRepository.saveUser(name, surname)
    }
}