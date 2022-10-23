package com.example.task4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.task4.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private var _binding: ActivityMainBinding? = null
    private val binding get() = _binding!!
    private val fragmentsContainerId: Int = R.id.fragments_container

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction()
            .add(fragmentsContainerId, MainFragment.getInstance(), MainFragment.MAIN_FRAGMENT_TAG)
            .commit()
    }
}