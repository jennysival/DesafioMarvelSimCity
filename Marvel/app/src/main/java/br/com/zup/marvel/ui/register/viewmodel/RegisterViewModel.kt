package br.com.zup.marvel.ui.register.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import br.com.zup.marvel.REGISTER_ERROR
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.repository.AuthRepository

class RegisterViewModel: ViewModel() {
    private val authRepository = AuthRepository()

    private var _registerState = MutableLiveData<User>()
    val registerState: LiveData<User> = _registerState

    private var _errorState = MutableLiveData<String>()
    val errorState: LiveData<String> = _errorState

    fun register(user: User){
        try {
            authRepository.registerUser(user.email,user.password)
                .addOnSuccessListener {
                    _registerState.value = user
                }
                .addOnFailureListener{
                    _errorState.value = REGISTER_ERROR
                }
        }catch (e: Exception){
            _errorState.value = REGISTER_ERROR
        }
    }
}