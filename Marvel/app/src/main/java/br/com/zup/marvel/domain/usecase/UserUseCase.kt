package br.com.zup.marvel.domain.usecase

import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.domain.repository.AuthRepository

class UserUseCase {
    private val authRepository = AuthRepository()

    fun register(user: User){
        authRepository.registerUser(user.email, user.password)
        authRepository.insertUserName(user.name)
    }

    fun login(user: User){
        authRepository.login(user.email, user.password)
    }

    fun getName(): String{
        return authRepository.getUserName()
    }

    fun logout(){
        authRepository.logout()
    }
}