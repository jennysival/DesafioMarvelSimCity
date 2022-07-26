package br.com.zup.marvel.ui.login.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.LOGIN_ERROR
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.repository.AuthRepository

class LoginViewModel: ViewModel() {
    private val authRepository = AuthRepository()

    private var _loginState = MutableLiveData<User>()
    val loginState: LiveData<User> = _loginState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun login(user: User){
        try {
            authRepository.login(user.email,user.password)
                .addOnSuccessListener { _loginState.value = user }
                .addOnFailureListener { _errorState.value = LOGIN_ERROR}
        }catch (e: Exception){
            _errorState.value = LOGIN_ERROR
        }
    }
}