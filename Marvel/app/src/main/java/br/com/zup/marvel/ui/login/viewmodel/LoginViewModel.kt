package br.com.zup.marvel.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.LOGIN_ERROR
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.usecase.UserUseCase

class LoginViewModel: ViewModel() {
    private val userUseCase = UserUseCase()

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun login(user: User){
        try {
            if(userUseCase.validateUserData(user)){
                userUseCase.login(user)
                _loginState.value = user
            }else{
                _errorState.value = LOGIN_ERROR
            }
        }catch (e: Exception){
            _errorState.value = LOGIN_ERROR
        }
    }
}