package br.com.zup.marvel.ui.register.viewmodel

import br.com.zup.marvel.domain.model.User
import org.junit.Assert.assertEquals
import org.junit.Test

class RegisterViewModelTest{

    @Test
    fun register(){
        val user = User(name = "Jenny", email = "jenny@mail.com", password = "123456789")
        assertEquals(user.name, "Jenny")
        assertEquals(user.email, "jenny@mail.com")
        assertEquals(user.password, "123456789")
    }
}