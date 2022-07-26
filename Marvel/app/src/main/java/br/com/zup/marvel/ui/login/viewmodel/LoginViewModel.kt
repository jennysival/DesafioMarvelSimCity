package br.com.zup.marvel.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.marvel.REGISTER_ERROR
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.usecase.UserUseCase

class LoginViewModel {
    private val userUseCase = UserUseCase()

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun login(user: User){
        if(userUseCase.validateUserData(user)){
            userUseCase.login(user)
            _loginState.value = user
        }else{
            _errorState.value = REGISTER_ERROR
        }
    }
}