package br.com.zup.marvel.domain.usecase

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import br.com.zup.marvel.LOGIN_ERROR
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.repository.AuthRepository

class UserUseCase {
    private val authRepository = AuthRepository()

    fun register(user: User){

        authRepository.registerUser(user.email, user.password)
            .addOnSuccessListener { authRepository.insertUserName(user.name) }
    }

    fun login(user: User){
        authRepository.login(user.email, user.password)
    }

    fun validateUserData(user: User): Boolean{
        return when{
            user.name.isEmpty() -> {
                false
            }
            user.name.length < 3 -> {
                false
            }
            user.email.isEmpty() -> {
                false
            }
            user.password.isEmpty() -> {
                false
            }
            user.password.length < 8 -> {
                false
            }
            else -> {
                true
            }
        }
    }

    fun getName(): String{
        return authRepository.getUserName()
    }

    fun logout(){
        authRepository.logout()
    }
}