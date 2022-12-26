package com.example.task_10.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.setupWithNavController
import com.example.task_10.R
import com.example.task_10.databinding.ActivityMainBinding
import com.example.task_10.db.DatabaseHandler
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {
    private lateinit var controller: NavController

    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        applicationContext?.let {
            DatabaseHandler.dbInit(appContext = it)
        }

        controller = (supportFragmentManager.findFragmentById(R.id.container) as NavHostFragment).navController
        val bottomView = findViewById<BottomNavigationView>(R.id.btn_view)
        bottomView.setupWithNavController(controller)

        controller.addOnDestinationChangedListener { _, dest, _ ->
            when (dest.id) {
                R.id.entryFragment -> bottomView.visibility = View.GONE
                R.id.registerFragment -> bottomView.visibility = View.GONE
                else -> bottomView.visibility = View.VISIBLE
            }
        }
    }
}