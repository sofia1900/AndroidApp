package com.iesam.fomapp.app


sealed class ErrorApp {
    object UnknowError : ErrorApp()

}