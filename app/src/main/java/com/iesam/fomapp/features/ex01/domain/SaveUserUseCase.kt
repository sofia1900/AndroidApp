package com.iesam.androidtrainning.domain

import com.iesam.androidtrainning.app.ErrorApp
import com.iesam.kotlintrainning.Either

class SaveUserUseCase (private val repository: UserRepository){

    operator fun invoke (input: Input ) : Either<ErrorApp, Boolean> {
        return repository.save(input)
    }


    //Se mete aqui porque solo se utiliza cuando se usa el caso de uso
    //Se deberia de llamar INPUT porque no es un modelo de algo, sino que son
    //datos en si.
    data class Input (val name : String, val userName : String, val age : String)

}

