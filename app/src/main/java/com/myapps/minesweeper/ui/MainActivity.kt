package com.myapps.minesweeper.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupActionBarWithNavController
import com.myapps.minesweeper.R
import com.myapps.minesweeper.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private var _binding:ActivityMainBinding?=null
    private val binding get() = _binding!!
    private lateinit var  navController: NavController
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        navController = findNavController(R.id.mainHomeFragment)
        val appBarConfiguration = AppBarConfiguration(
            setOf(
                R.id.mainHomeFragment,
                R.id.gameFragment
            )
        )
        supportActionBar?.hide()
        setupActionBarWithNavController(navController,appBarConfiguration)
    }

    override fun onSupportNavigateUp(): Boolean {
        return super.onSupportNavigateUp() || navController.navigateUp()
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }
}