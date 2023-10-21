package com.iesam.fomapp.features.ex04.data.remote

import com.iesam.fomapp.app.Either
import com.iesam.fomapp.app.ErrorApp
import com.iesam.fomapp.app.right
import com.iesam.fomapp.features.ex04.domain.Burger

class ApiMockRemoteDataSource {

    fun getBurgerMock() : Either<ErrorApp, Burger>{
        return Burger("-15%", "Burger It","98%", "20-30min").right()
    }
}