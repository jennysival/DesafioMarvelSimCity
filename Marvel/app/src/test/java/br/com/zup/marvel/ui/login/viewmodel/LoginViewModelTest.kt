package br.com.zup.marvel.ui.login.viewmodel

import br.com.zup.marvel.domain.model.User
import org.junit.Assert.*
import org.junit.Test

class LoginViewModelTest{

    @Test
    fun login(){
        val user = User(email = "jenny@mail.com", password = "123456789")
        assertEquals(user.email, "jenny@mail.com")
        assertEquals(user.password, "123456789")
    }
}