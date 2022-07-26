package br.com.zup.marvel.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.marvel.REGISTER_ERROR
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.usecase.UserUseCase

class RegisterViewModel {
    private val userUseCase = UserUseCase()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun register(user: User){
        if(userUseCase.validateUserData(user)){
            userUseCase.register(user)
            _registerState.value = user
        }else{
            _errorState.value = REGISTER_ERROR
        }
    }
}