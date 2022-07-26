package br.com.zup.marvel.ui.home.view

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import br.com.zup.marvel.*
import br.com.zup.marvel.databinding.ActivityHomeBinding
import br.com.zup.marvel.ui.detalhe.DetalheActivity
import br.com.zup.marvel.data.model.Marvel
import br.com.zup.marvel.ui.home.viewmodel.HomeViewModel
import br.com.zup.marvel.ui.login.view.LoginActivity

class HomeActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHomeBinding

    private val adapter: MarvelAdapter by lazy {
        MarvelAdapter(arrayListOf(), this::goToDetail)
    }

    private val viewModel: HomeViewModel by lazy {
        ViewModelProvider(this)[HomeViewModel::class.java]
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)

        showGreetings()
        viewModel.getListMarvel()
        setUpRecyclerView()
        initObserver()
    }

    private fun showGreetings(){
        val username = viewModel.getUserName()
        val greetingsText = buildString {
            append("Olá ")
            append(username)
            append(", esses são alguns dos personagens da Marvel")
        }
        binding.textView.text = greetingsText
    }

    private fun setUpRecyclerView() {
        binding.rvHerois.adapter = adapter
        binding.rvHerois.layoutManager = LinearLayoutManager(this)
    }

    private fun initObserver(){
        viewModel.marvelListState.observe(this){
            adapter.updateMarvelList(it.toMutableList())
        }
    }

    private fun goToDetail(marvel: Marvel) {
        val intent = Intent(this, DetalheActivity::class.java).apply {
            putExtra(MARVEL_KEY, marvel)
        }
        startActivity(intent)
    }

    private fun navigateToLogin(){
        startActivity(Intent(this, LoginActivity::class.java))
    }
    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.menulogout, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_exit -> {
                viewModel.logout()
                this.finish()
                navigateToLogin()
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

}