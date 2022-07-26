package br.com.zup.marvel.ui.register.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.*
import br.com.zup.marvel.databinding.ActivityRegisterBinding
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.register.viewmodel.RegisterViewModel

class RegisterActivity : AppCompatActivity() {
    private lateinit var binding: ActivityRegisterBinding

    private val viewModel: RegisterViewModel by lazy {
        ViewModelProvider(this)[RegisterViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityRegisterBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Registro"

        binding.btnRegister.setOnClickListener {
            registerButtonClick()
        }

        initObserver()
    }

    private fun registerButtonClick(){
        val user = getUserData()

        if(user != null){
            viewModel.register(user)
        }
        else{
            showErrorMessage()
        }

    }

    private fun getUserData(): User?{
        val user = User(
            name = binding.etName.text.toString(),
            email = binding.etEmail.text.toString(),
            password = binding.etPassword.text.toString()
        )

        return if(user.name.isEmpty() || user.email.isEmpty() || user.password.isEmpty()){ null }
        else{ user }
    }

    private fun showErrorMessage(){
        if(binding.etName.text.isEmpty()){
            binding.etName.error = NAME_ERROR
        }

        if(binding.etEmail.text.isEmpty()){
            binding.etEmail.error = EMAIL_ERROR
        }

        if(binding.etPassword.text.isEmpty()){
            binding.etPassword.error = PASSWORD_ERROR
        }

        if(binding.etName.text.length in 1..2){
            binding.etName.error = NAME_LENGHT_ERROR
        }

        if(binding.etPassword.text.length in 1..7){
            binding.etPassword.error = PASSWORD_LENGHT_ERROR
        }
    }

    private fun navigateToHome(user: User){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(BUNDLE_USER, user)
        startActivity(intent)
    }

    private fun initObserver(){
        viewModel.registerState.observe(this){
            navigateToHome(it)
        }

        viewModel.errorState.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }

    }
}