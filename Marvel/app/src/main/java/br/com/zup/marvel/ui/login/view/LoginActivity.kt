package br.com.zup.marvel.ui.login.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import br.com.zup.marvel.*
import br.com.zup.marvel.databinding.ActivityLoginBinding
import br.com.zup.marvel.domain.model.User
import br.com.zup.marvel.ui.home.view.HomeActivity
import br.com.zup.marvel.ui.login.viewmodel.LoginViewModel
import br.com.zup.marvel.ui.register.view.RegisterActivity

class LoginActivity : AppCompatActivity() {
    private lateinit var binding: ActivityLoginBinding

    private val viewModel: LoginViewModel by lazy {
        ViewModelProvider(this)[LoginViewModel::class.java]
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title = "Login"

        binding.tvNewAccount.setOnClickListener {
            navigateToRegister()
        }

        binding.btnLogin.setOnClickListener {
            loginButtonClick()
        }

        initObserver()
    }

    private fun navigateToRegister(){
        startActivity(Intent(this, RegisterActivity::class.java))
    }

    private fun navigateToHome(user: User){
        val intent = Intent(this, HomeActivity::class.java)
        intent.putExtra(BUNDLE_HOME_USER, user)
        startActivity(intent)
    }

    private fun loginButtonClick(){
        val user = getLoginData()

        if(user != null){
            viewModel.login(user)
        }
        else{
            showErrorMessage()
        }
    }

    private fun getLoginData(): User?{
        val user = User(
            email = binding.etEmailLogin.text.toString(),
            password = binding.etPasswordLogin.text.toString()
        )

        return if(user.email.isEmpty() || user.password.isEmpty()){ null }
        else{ user }
    }

    private fun showErrorMessage(){
        if(binding.etEmailLogin.text.isEmpty()){
            binding.etEmailLogin.error = EMAIL_ERROR
        }

        if(binding.etPasswordLogin.text.isEmpty()){
            binding.etPasswordLogin.error = PASSWORD_ERROR
        }
    }

    private fun initObserver(){
        viewModel.loginState.observe(this){
            navigateToHome(it)
        }

        viewModel.errorState.observe(this){
            Toast.makeText(this, it, Toast.LENGTH_LONG).show()
        }
    }
}