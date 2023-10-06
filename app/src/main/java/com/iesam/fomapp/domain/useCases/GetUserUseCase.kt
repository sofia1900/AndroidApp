package com.iesam.fomapp.domain.useCases

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.data.UserDataRepository
import com.iesam.fomapp.domain.User

class GetUserUseCase (private val userDataRepository: UserDataRepository) {

    operator fun invoke () : Either<ErrorApp, User> {
        return userDataRepository.getUser()
    }
}