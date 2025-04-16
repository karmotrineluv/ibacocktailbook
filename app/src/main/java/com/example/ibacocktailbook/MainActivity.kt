package com.example.ibacocktailbook

import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.ibacocktailbook.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Скрываем ActionBar
        supportActionBar?.hide()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.nav_host_fragment) as NavHostFragment
        val navController = navHostFragment.navController

        // Скрываем/показываем BottomNavigationView в зависимости от текущего фрагмента
        navController.addOnDestinationChangedListener { _, destination, _ ->
            when (destination.id) {
                R.id.loginFragment,
                R.id.registerFragment,
                R.id.resetPasswordFragment -> {
                    binding.bottomNavigationView.visibility = View.GONE // Скрыть навигацию
                }
                else -> {
                    binding.bottomNavigationView.visibility = View.VISIBLE // Показать навигацию
                }
            }
        }

        // Привязка навигации с BottomNavigationView
        binding.bottomNavigationView.setupWithNavController(navController)
    }
}
